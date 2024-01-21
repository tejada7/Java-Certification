# CKAD studying notes

### Kubernetes high-level architecture

Kubernetes is a production-ready runtime environment for managing containerized applications at scale. Ideally for
operating microservice architectures, it also supports non-functional requirements such load balancing, security and 
scalability.

```mermaid
flowchart TD
;
    subgraph cluster[Kubernetes cluster]
        direction LR
        subgraph controlPlane[Control Plane Node]
            api[API Server]
            scheduler[Scheduler]
            controller[Controller manager]
            etcd[etcd]
        end
        controlPlane:::green
        direction BT
        subgraph worker1[Worker Node 1]
            kubelet[kubelet]:::type
            kubeProxy[Kube proxy]
            containerRuntime[Container runtime]
        end
        worker1:::blue
        subgraph worker2[Worker Node n]
            kubelet1[...]
        end
        worker2:::blue
        controlPlane --> worker1
        controlPlane --> worker2
        classDef green fill: green
        classDef blue fill: #08427b
    end
```

- **Control plane node** → exposes the Kubernetes API via the API server and manages the nodes within the cluster.
  It also responds to cluster events
    - **API server** → the exposed endpoints to communicate with the cluster
    - **Scheduler** → a process that identifies new non-assigned-node pods to assign them to a worker node for execution
    - **Controller manager** → checks the state of the cluster and implements changes
    - **Etcd** → a key-value store used to persist all data related to the cluster
- **Worker node** → executes workloads in containers within pods. Each work node must have a container runtime engine
  to be able to manage containers
    - **kubelet** → agent that makes sure that containers are running in a pod, in other words, it links Kubernetes to
      the container runtime engine by ensuring that containers are running healthy. It can also be present in the control
      plane node although inconventional (due to the existence of a Taint on the master node `kubectl describe node kubemaster | grep Taint`)
    - **Kube proxy** → network proxy allowing network communication and keeping network rules. It also implements the
      svc
    - **Container runtime** → responsible for managing containers

#### Objects

ℹ️ Resources, objects or primitives in Kubernetes are either namespaced or cluster-scoped. `kubectl api-resources --namespaced=true`
```mermaid
flowchart TD
;
    subgraph frame[Kubernetes object structure]
        direction LR
        subgraph kubObject[Kubernetes object]
            description1["API Version v1, apps/v1, ...
                        Kind Pod, Deployment, Quota
            ------------------------------------
            Metadata
            Name, Namespaces, Labels, ...
            ------------------------------------
            Spec
            Desired state
            ------------------------------------
            Status
            Actual state"]
        end
    kubObject:::blue
    description1:::alignLeft
    subgraph yaml[Yaml manifest]
        description2["api_version: v1
        kind: Pod
        metadata:
        #nbsp; #nbsp; labels:
        #nbsp; #nbsp; #nbsp; #nbsp; run: nginx
        #nbsp; #nbsp; name: nginx
        spec:
        #nbsp; #nbsp; containers:
        #nbsp; #nbsp; - image: nginx:latest
        #nbsp; #nbsp; #nbsp; name: nginx
        status: #123; &rbrace;"]:::yamlText
    end
    yaml:::blue
    kubObject --> yaml
    classDef green fill: green
    classDef blue fill:#08427b;
    classDef yamlText text-align-last: left, text-align: justify, text-justify: distribute;
    %%        classDef yamlText text-justify: left;
    classDef alignLeft text-align: left;
end
```

- **API version** → defines the structure of the primitive
- **Kind** → defines type of primitive
- **Metadata** → contains higher-level information about the object, such as label and name
- **Spec** → defines the desired state after the object has been created
- **Status** → describes the actual state of the object. If no status is set, its value is `{}`

## kubectl

```shell
kubectl [command] [type] [name] [flags]
          get       po    app   -o yaml
          run            my_pod  --image=nginx --port=80
```

```shell
kubectl api-resources # find all available commands with their abbreviations
kubectl api-versions # list all api versions in the format group/version
kubectl config view 
```

### Updating objects

```shell
kubectl edit po my_pod
kubectl patch po my_pod -p '{"spec":{"containers":[{"name":"my_pod", "image":"nginx:1.25"}]}}'
kubectl delete po my_pod --now # forces deletion disabling graceful deletion  
```

### Creating objects

```shell
kubectl apply -f deployment.yaml # creates an object from a manifest file
kubectl apply -f /creation # creates object(s) from a directory containing manifest files
kubectl apply -f /root -R  # creates object(s) from a directory containing manifest files recursively
kubectl apply -f https://raw.githubusercontent.com/tejada7/deployment.yaml # creates an object from a URL 
```

### Hybrid approach

```shell
kubectl run my_pod --image=nginx \
 --port=80 \
 -o yaml \
 --dry-run=client > pod.yaml # mind that --dry-run without any value is deprecated
 --labels=app=backend,env=dev
 -- "arg1" "arg2" ... # or --command -- "command" "arg1" "arg2"
```

### Getting container runtime

```shell
kubectl get nodes -o wide
```

### Pod life cycle phases

```mermaid
flowchart TD
;
    Pending --> Running --> Succeeded
    Running --> Failed
    Unknown
```

- **Pending** → the pod has been accepted by the Kubernetes cluster, but containers aren't being created
- **Running** → at least one container is running, or is starting or restarting
- **Succeeded** → all containers in the pod terminated successfully
- **Failed** → at least one pod failed with an error
- **Unknown** → the state of the pod could not be obtained

ℹ️ Pod's default restart policy (i.e. `spec.restartPolicy`) is `Always`, which tells Kubernetes scheduler
to *always* restart the pod even if the container exits.

### Pod details

```shell
kubectl describe po my_pod
kubectl get po my_pod
kubectl get po --show-labels
kubectl get po my_pod -o wide
kubectl get po my_pod -o yaml

# custom output, allowing to get the pod name as well as its IP, ordered by ip
kubectl get po -A -o custom-columns=POD_NAME:.metadata.name,IP_ADDR:.status.podIP --sort-by=.status.podIP
```

### Accessing logs

```shell
kubectl logs my_pod
kubectl logs my_pod -f # follow logs in real time
kubectl logs my_pod --previous # logs of the previous run
kubectl logs my_pod -c container1 # logs of the container 1 in case of many within the Pod
```

### Executing commands in containers

```shell
kubectl exec -it my_pod -- /bin/sh
kubectl exec -it my_pod -- bash
kubectl exec my_pod -- env
```

ℹ️ `--` separates the `exec` command and its options from the command to be run inside the container

### Requesting an IP within a pod

```shell
# instead of wget, we could also use curl
kubectl run busybox --image=busybox --rm -it --restart=Never -- wget xxx.xxx.xxx.xxx:80 
```

These are equivalent:
```mermaid
flowchart TD
;
subgraph frame[ ]
    direction LR
    subgraph example1[ ]
        description1["
        apiVersion: v1
        kind: Pod
        metadata:
        #nbsp; #nbsp; name: my-pod
        spec:
        #nbsp; #nbsp; containers:
        #nbsp; #nbsp; - args:
        #nbsp; #nbsp; #nbsp; #nbsp; - /bin/sh
        #nbsp; #nbsp; #nbsp; #nbsp; - -c
        #nbsp; #nbsp; #nbsp; #nbsp; - while true; do date; sleep 10; done
        #nbsp; #nbsp; image: busybox:1.36.1
        #nbsp; #nbsp; name: my-pod"]
    end
    example1:::color
    description1:::alignLeft
    subgraph example2[ ]
        description2["
        apiVersion: v1
        kind: Pod
        metadata:
        #nbsp; #nbsp; name: my-pod
        spec:
        #nbsp; #nbsp; containers:
        #nbsp; #nbsp; - command: #91;#34;/bin/sh#34;#93;
        #nbsp; #nbsp; #nbsp; args: #91;#34;-c#34;, #34;while true; do date; sleep 10; done#34;#93;
        #nbsp; #nbsp; #nbsp; image: busybox:1.36.1
        #nbsp; #nbsp; #nbsp; name: my-pod"]:::yamlText
    end
    example2:::color
    example1 --> example2
    classDef color fill:#08421b;
    classDef yamlText text-align-last: left, text-align: justify, text-justify: distribute;
    classDef alignLeft text-align: left;
end
```

### Namespaces

```shell
kubectl config set-context --current --namespace=nsName

kubectl config use-context <context-name> # to switch between contexts
```

### Jobs and Cronjobs

ℹ️ There is a way to auto-cleanup jobs by specifying the attribute `spec.ttlSecondsAfterFinished`

ℹ️ Jobs operate in three modes:

- non-parallel with one completion count (default)
- parallel with a fixed completion count
- parallel with worker queue

ℹ️ The attribute `spec.completions` controls the number of required successful completions, whereas the attribute
`spec.parallelism` allows execution in parallel

#### Creation

```shell
kubectl create job counter --image=nginx -- /bin/sh 
-c 'counter=0; while [$counter -lt 3 ]; do counter=$((counter+1)); echo "$counter"; sleep 3; done;'
```

```shell
kubectl create cronjob now --schedule="* * * * *" --image=nginx -- /bin/sh 
-c 'echo "Current date: $(date)"'
```

#### Getting number of completions

```shell
kubectl get jobs counter -o yaml | grep -C 1 "completions"
```

#### Restart behavior

ℹ️ The `spec.backoffLimit` attribute determines the number of retries a Job tries to successfully complete (i.e. exit
code = 0). It's defaulted to **6**

ℹ️ The restart policy of a Job can be either `OnFailure` or `Never`

### Volumes

- *Ephemeral volumes* exist only throughout the lifespan of the Pod. Useful when wanting to share data among multiple
  containers running within the same Pod. It gets cleaned up and reconstructed upon Pod restart
- *Persistent volumes* preserve data beyond the lifespan of a Pod

ℹ️ StorageClasses allow to automatically create pvs when using a cloud provider. It then can be referenced in the pvc definition. 
```yaml
apiVersion: storage.k8s.io/v1
kind: StorageClass
metadata:
  name: local-storage
provisioner: kubernetes.io/no-provisioner
reclaimPolicy: Delete
volumeBindingMode: WaitForFirstConsumer
```

#### Common volume types

| Type                     | Description                                                                       |
|--------------------------|-----------------------------------------------------------------------------------|
| `emptyDir`               | Empty directory within the Pod with R/W access. Only valid for the Pod's lifespan |
| `hostPath`               | File or directory from the host node's filesystem                                 |
| `configMap`, `secret`    | Deals with configuration data                                                     |
| `nfs`                    | Network file system, preserves data after Pod restart                             |
| `persistenceVolumeClaim` | Claims a persistent volume                                                        |

To define an ephemeral volume:
```yaml
apiVersion: v1
kind: Pod
metadata:
  name: business-app
spec:
  volumes: # 1 define the volume name and its type
  - name: my-volume
    emptyDir: {}
  containers:
  - image: nginx
    name: nginx
    volumeMounts: # 2 the defined volume must be mounted to a path of the container
    - mountPath: /var/my-volume
      name: my-volume
```

#### Creating PersistentVolume
ℹ️ Every PersistentVolume needs to define the storage capacity (`spec.capacity`) as well as an access mode
(`spec.accessMode`)

⚠️ `kubectl` doesn't allow to create pv directly

```yaml
apiVersion: v1
kind: PersistentVolume
metadata:
  name: db
spec:
  capacity:
    storage: 1Gi
  accessModes:
    - ReadWriteOnce
  hostPath:
    path: /data/db
```

| Type               | Alias | Description                               |
|--------------------|-------|-------------------------------------------|
| `ReadWriteOnce`    | RWO   | Read/write access by a single node        |
| `ReadOnlyMany`     | ROX   | Read-only access by many nodes            |
| `ReadWriteMany`    | RWX   | Read/write access by many nodes           |
| `ReadWriteOncePod` | RWOP  | Read/write access mounted by a single Pod |

#### Creating PersistentVolumeClaim
Its purpose is to bind the PersistentVolume to the Pod.
```yaml
kind: PersistentVolumeClaim
apiVersion: v1
metadata:
  name: my-pvc
spec:
  accessModes:
    - ReadWriteOnce
  storageClassName: "" # important to leave as empty string if no storageClass to bind
  resources:
    requests:
      storage: 256Mi
```
The above definition looks for a PersistentVolume that can fulfill the resource request of 256Mi providing the mode 
`ReadWriteOnce`. If found, then the pvc status is set to `Bound` and the last step requires to bind the Pod to pvc:
```yaml
apiVersion: v1
kind: Pod
metadata:
  name: pod-consuming-pvc
spec:
  volumes:
    - name: pod-storage
      persistentVolumeClaim: # mind this section
        claimName: my-pvc # so does it, that must match the name of the previously created pvc
  containers:
  - image: nginx
    name: my-pod
    command: ["/bin/sh"]
    args: ["-c", "while true; do sleep 60; done;"]
    volumeMounts:
      - mountPath: "/tmp/data"
        name: pod-storage
```
### Multi-container pods
- **sidecar** → a container that enhances the primary application, for example logging 
- **ambassador** → a container that represents the primary container to the outside world, such as a proxy
- **adapter** → used to adapt the traffic or data pattern to match the traffic 

### Labels, selectors and annotations
```shell
kubectl describe describe pod labeled-pod  | grep -C 2 Labels:
kubectl get pods --show-labels
kubectl label pod labeled-pod region=bo # to add a label region to the existing pod
kubectl label pod labeled-pod region=eu --overwrite # overrides an existing label
kubectl label pod labeled-pod region- # removes the label region
kubectl get po -l key=value --show-labels # retrieve the pods with the label key=value, or --selector instead of -l
kubectl get po --selector 'key in (value1, value2)' # retrieve those pods whose key value is either value1 or value2 
```
Unlike labels (i.e. `metadata.labels`), annotations (i.e. `metadata.annotations`) cannot be added via `kubectl run 
--label=...`; however, what can be done is modification:
```shell
kubectl annotate pod my-pod key='value'
kubectl annotate pod my-pod key='value1' --overwrite
kubectl annotate pod my-pod key-
```
### Pod security standards

Configurable via the `pod-security.kubernetes.io/enforce: "baseline"`annotation 

| Profile     | Description                 |
|-------------|-----------------------------|
| Privileged  | Unrestricted policy         |
| Baseline    | Minimally restricted policy |
| Restrictive | Heavily restricted policy   |

### Deployments
When creating a deployment imperatively (i.e. `kubectl create deploy deploy-name ...`), `app` is the label key used by
the deployment to identify the related replicas and pods. It can be find in three different sections of the manifest:
- `metadata.labels`
- `spec.selector.matchLabels`
- `spec.template.metadata.labels`

ℹ️ For label selection to work properly, the last two of the above must match.

#### Updating a deployment
```shell
kubectl edit deployment my-deployment
kubectl set image deployment my-deployment container-name:new-image
kubectl patch deployment my-deployment -p '{"spec": "template": {"spec": {"containers": [{"name": "nginx", "image":"newImage"}]}}}}'
```

ℹ️ The *rolling update strategy* consists in the deployment taking care of updating all replicas to the new version one at 
a time.

```shell
kubectl rollout status deploy my-deployment # checks whether all the replicas were successfully rolled out
kubectl rollout history deploy my-deployment # gets the history of rollouts in sequential order (i.e. revision 1 was before revision 2 and so on...)
kubectl rollout history deploy my-deployment --revision 1 # gets the detailed description of the revision 1
```

#### Deployment strategies

| Type                               | Description                                                                                                                                                                                                                                                                                        |
|------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Ramped - `RollingUpdate` (default) | It means that two versions of the same application coexist during update process. In that sense, it's advisable to develop backward-compatible applications.                                                                                                                                       |
| Fixed - `Recreate`                 | Kills all pods first, then creates Pods with the newest version. ⚠️ Beware of downtime                                                                                                                                                                                                             |
| Blue-green aka red-black           | Two deployments in parallel: blue represents the old version, and green the new one, upon validation of the green version, the svc can now target the green deployment and hence scale down or delete the blue one. This strategy is convenient for complex upgrades without downtime to consumers |
| Canary                             | Similar to the blue-green deployment in the sense that two deployments co-exist. The only difference is that the newer version is targeted to a subset of consumers. This is useful for experimental features                                                                                      |

#### Adding change cause for revision
```shell
kubectl annotate deployment my-deployment kubernetes.io/change-cause="reason for the rollout"
```

#### Rolling back to a previous revision
```shell
kubectl rollout undo deploy my-deployment --to-revision=1 # rolls back deployment to revision 1
```

#### ⚠️ Deprecated - Recording the exact change rolling update
```shell
kubectl set image deploy nginx nginx=nginx:1.17 --record
```


#### Scaling 
```shell
kubectl scale deploy my-deployment --replicas=3 # scales up the number of replicas to 3
```
#### Horizontal Pod auto scaler (HPA)
```shell
kubectl autoscale deploy my-deployment --cpu-percent=80 --min=3 --max=5
```
### Helm
It's a templating engine and package manager for Kubernetes. 
Charts can be found at [https://artifacthub.io/](https://artifacthub.io/)

```shell
helm repo add my-repo https://charts.my-repo.io # downloads the repo

helm install foo foo/my-repo --version 1.0.0 # installs foo with version 1.0.0

helm install foo foo/my-repo --version 1.0.0 --set spec.username=boss --set spec.password=password -n custom-ns --create-namespace # installs foo with version 1.0.0, overloading the properties username and password, as well as creating the namespace custom-ns 

helm repo update
helm upgrade foo foo/my-repo --version 2.0.0 # upgrades the chart to version 2.0.0

helm uninstall foo # uninstalls the chart 

helm search hub <release-name> # searches for an available chart on the hub

helm search repo <release-name> # searches for an available chart on the local repo

helm pull --untar repo # downloads a repo locally

helm install <release-name> . # installs it from a downloaded chart
``` 

### Probes
```yaml
apiVersion: v1
kind: Pod
metadata:
  name: probes-pod
spec:
  containers:
  - image: nginx
    name: my-container
    ports:
    - name: web-port
      containerPort: 80
    args:
      - /bin/sh
      - -c
      - 'while true; do touch /tmp/heartbeat.txt; sleep 5; done;'
    startupProbe:
      tcpSocket:
        port: 80
      initialDelaySeconds: 3
      periodSeconds: 15
    readinessProbe:
      httpGet:
        path: /
        port: web-port # instead of using the port, we can reference the name
      initialDelaySeconds: 2
      periodSeconds: 8
    livenessProbe:
      exec:
        command:
          - test `find /tmp/heartbeat.txt -mmin -1`
      initialDelaySeconds: 5
      periodSeconds: 30
```

### How to restart a deployment
```shell
# Method 1
kubectl rollout restart deploy my-deployment

# Method 2
kubectl scale rs my-rs --replicas=0 # scales down
kubectl scale rs my-rs --replicas=4 # scales up

# Method 3
kubectl delete po pod-1 --now # deleting every single outdated instance of the pod to get restarted by the rs
```

### DNS
```shell
my-service.my-namespace.svc.cluster.local # automatically created
service-name.namespace.service.domain
```

### Services

| Type         | Description                                                                                                                                                                                                                                                     |
|--------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| ClusterIP    | Service can only be reached by other applications within the cluster. The most common and default type.                                                                                                                                                         |
| NodePort     | The Service is exposed as a static port on each Node in the cluster. Access from outside the cluster to the Service is via `<NodeIP>:<NodePort>`. Each NodePort is unique to the cluster at the default range 30000-32767. Accessible from outside the cluster. |
| LoadBalancer | Connects an external load balancer from your cloud to the Service.                                                                                                                                                                                              |
| ExternalName | Access for in-cluster applications to reach other services in a different namespace, a different cluster, or some application outside the clusters, such as a cloud service. Maps a service to a DNS name.                                                      |

```shell
# Creating a service independently from the object to expose
kubectl create svc [nodeport | clusterip | loadbalancer] my-service --tcp=<port>:<targetPort> # wherein service type can be clusterip, externalname, loadbalancer or nodeport 
                    

# Exposing a resource as service
kubectl expose <object> --name=my-service --port=80 --target-port=3000 [--type=NodePort] # wherein <object> can be po, deploy, rs

# Creating a pod and exposing it as service
kubectl run my-pod --image=<image> --restart=Never --port=80 --expose

# Updating a ClusterIP to a NodePort
kubectl patch svc my-service -p='{"spec:": {"type": "NodePort"}}'
```

```mermaid
flowchart TD
;
    top["Incoming traffic"] --> port 
    subgraph port[3000 - node port]
        direction TB
        service[service\n80 - targetPort]
    end
    subgraph Pod[80]
        minipod[Pod]
    end
    service --> Pod[80]
    subgraph Pod1[80 - container port]
        minipod1[Pod]
    end
    service --> Pod1

```
ℹ️ Mind that if not specified, target port with get the same value as port, whereas nodeport (in case of a svc of type=NodePort)
will get a random IP from the range 3xxxx
### Some Docker useful commands
```shell
docker images # lists the available images

docker container ls # lists the running containers (add -a to display all existing ones)

docker build -t imageName:tag .

docker run -p <host-port>:<container-port> imageName:tag -d  
```

The argument of the below dockerfile can be overriden by running `docker run my-image 10`
```dockerfile
FROM ubuntu

ENTRYPOINT["sleep"]

CMD["5"]
```

However, if we ever want to override the executable (i.e. `sleep`), then `docker run my-image --entrypoint cat /etc/*release*`

The correspondence to a pod definition is:

![img.png](docker_kube_entrypoint_cmd.png)

### Config maps
```shell
kubectl create configmap my-config-map --from-literal=<key>=<value>
kubectl create configmap my-config-map --from-file=<path-to-file>
```

```yaml
spec:
  containers:
    - name: my-container
      image: image-name
      ports:
        - containerPort: 8080
      envFrom:
        - configMapRef:
            name: my-config-map # This is how we inject a cm to a pod
        - name: MY_ENV_VAR
          valueFrom:
            configMapKeyRef:
              name: my-config-map
              key: PROPERTY_KEY
```

### Secrets

```shell
kubectl create secret generic <secret-name> --from-literal=<key>=<value>
kubectl create secret generic <secret-name> --from-file=<path-to-file>

# to encode base 64
echo -n 'secret' | base64
echo -n 'bxosi==' | base64 --decode

# to read a secret
kubectl get secret <secret-name> --template={{data.SECRET_NAME}} | base64 -d
```

```yaml
spec:
  containers:
    - name: my-container
      image: image-name
      ports:
        - containerPort: 8080
      env:
        - name: PASSWORD
          valueFrom:
            secretKeyRef:
              name: <secret-name>
              key: PASSWORD
        - name: MY_NODE_NAME
          valueFrom:
            fieldRef:
              fieldPath: spec.nodeName # this is useful if we need to expose additional information from the existing manifest
      envFrom:
        - secretRef:
            name: <secret-name>
```

### Security Context
```yaml
spec:
  securityContext:
    runAsUser: 1010
  containers:
  - image: ubuntu
    name: web
    securityContext:
      capabilities:
        add: ["SYS_TIME"] # This is only available at this level and not on spec.securityContext
```

### Service accounts
ℹ️ Every namespace has a service account, containing a secret token, from version 1.22 onwards, a token is not 
automatically created upon service account creation and instead a TokenRequest API is recommended i.e. `kubectl create token`

### Resource management
ℹ️ When configuring the cpu, the measure can be as low as 1m (wherein m stands for milli), (or .01 equals to 100m).

ℹ️ One count of cpu is equal to a core of cpu or vcpu, depending on the cloud provider.

As per the memory, it's measured using Kibibytes:

| Measure | Total in bytes      |
|---|---------------------|
| 1G (Gigabyte)  | 1 000 000 000 bytes |
| 1M (MegaByte)  | 1 000 000 bytes     |
| 1K (Kilobyte)  | 1 000 bytes         |
| 1Gi (Gibibyte) | 1 073 741 824 bytes |
| 1Mi (Mebibyte) | 1 048 576 bytes     |
| 1Ki (Kibibyte)  | 1 024 bytes         |

ℹ️ If a Pod exceeds the max cpu allotted, it throttles, whereas, if it exceeds the maximum memory, it terminates with an OOM error.    

ℹ️ By default, k8s does not set a resource limit, meaning that any Pod can consume as many resources as required within a 
node and suffocate others Pods or processes running. 

[This](https://hub.docker.com/r/polinux/stress-ng) is an awesome image used to test the resource limits.

ℹ️ Taints are set on nodes, whereas tolerations on Pods.

```shell
kubectl taint nodes <node-name> key=value:taint-effect (NoSchedule | PreferNoSchedule | NoExecute)
```

```yaml
...
kind: Pod
spec:
  containers:
  - image: nginx
    name: nginx
  tolerations:
  - key: app
    operator: "Equal"
    value: "blue"
    effect: "NoSchedule"
```

_Taints tell Nodes to accept Pods with certain tolerations._

### Node selectors and node affinity
```shell
kubectl label nodes <node-name> <key>=<label>
```

```yaml
...
kind: Pod
spec:
  affinity:
    nodeAffinity:
      requiredDuringSchedulingIgnoredDuringExecution:
        nodeSelectorTerms:
        - matchExpressions:
          - key: disktype
            operator: In
            values:
            - ssd            
  containers:
  - name: nginx
    image: nginx
    imagePullPolicy: IfNotPresent
```
### Monitoring

```shell
# displays the resource consumption
kubectl top nodes
kubectl top po
```

### Ingress
```shell
kubectl create ingress <ingress-name> --rule="host/path=service:port"

# example
k create ingress testingress --rule="/watch=video-service:8080" --rule="/wear=wear-service:8080" -n app-space
```
```yaml
apiVersion: extensions/v1beta1
kind: Ingress
metadata:
  name: test-ingress
  namespace: my-ns
  annotations:
    nginx.ingress.kubernetes.io/rewrite-target: / # important to avoid forwarding the path to the Pod
spec:
  rules:
  - http:
      paths:
      - path: /path
        backend:
          serviceName: my-service
          servicePort: 8080
```
#### Steps to setup nginx as ingress controller
1. Create a ns called `ingress-nginx` to isolate the ingress objects → `kubectl create ns ingress-nginx`
2. Create a cm called `ingress-nginx-controller` in the same ns → `kubectl create cm ingress-nginx-controller -n ingress-nginx`
3. Create 2 sa → `kubectl create sa ingress-nginx -n ingress-nginx` && `kubectl create sa ingress-nginx-admission -n ingress-nginx`
4. Setup Roles, RoleBindings, ClusterRoles, and ClusterRoleBindings for the ServiceAccount.

### Network policies
```yaml
apiVersion: networking.k8s.io/v1
kind: NetworkPolicy
metadata:
  name: internal-policy
  namespace: default
spec:
  podSelector:
    matchLabels:
      name: internal
  ingress:
  - from:
    - podSelector:
        matchLabels:
          name: external
    ports:
      - port: 8080
        protocol: TCP
  egress:
  - to:
    - podSelector:
        matchLabels:
          name: external
    ports:
    - port: 8080
      protocol: TCP
  - to:
    - podSelector:
        matchLabels:
          name: mysql
    ports:
    - port: 3306
      protocol: TCP
  policyTypes:
  - Ingress
  - Egress
```
### Authorization
##### Modes

- Node → authorization within the cluster
- ABAC (attribute-based access control) → a user with a set of permissions
- RBAC (role-based access control) → a role defined to be assigned to a bunch of users
- Webhook → 3rd party solutions

ℹ️ Roles are named-spaced. 

```shell
kubectl auth can-i create deploy # to check whether the connected user is allowed to perform an action (create a deployment in this case)
kubectl auth can-i delete pods --as dev-user # to check whether dev-user is allowed to perform an action
```

##### Creating roles
```shell
kubectl create role developer --namespace=default --verb=list,create,delete --resource=pods 
```

##### Creating role bindings
```shell
kubectl create rolebinding dev-user-binding --namespace=default --role=developer --user=dev-user
```

### Admission control
There are multiple ways to strengthen security, one of which is admission control which for instance hinders resources 
from being created on unknown namespaces by default.

To set it up, we must refer to the kube-api-server pod definition wherein we must add the line `--enable-admission-plugins=NamespaceAutoProvision`
in the command

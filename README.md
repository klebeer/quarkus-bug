**Maven Multi module CDI Injection_ClientProxy ClassCastException**

This is a simple project to reproduce a bug (or a misunderstood of how quarkus works)

*Environment:* 

* Oracle jdk1.8.0_211
* OS name: "mac os x", version: "10.14.4", arch: "x86_64", family: "mac"
* Apache Maven 3.6.0
* Quarkus 0.14.0


I have a maven multi-module project, one project is an "API" with only _interfaces_.
Second project is the "API Implementation", this project contains a Bean with **@ApplicationScoped** annotation.
Third project is a Rest Resource injecting with CDI the "API".


```
main-project
  -api
  -api-impl
  -rest
```


In the rest layer there is a CDI injection of this "API"

```
   @Inject
   HelloService helloService;


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{name}")
    public String hello(@PathParam("name") String name) {
        return helloService.sayHello(name);
    }
    
```


Steps to reproduce:
```
mvn clean install
```

```
cd buggy-app/
```

```
 mvn compile quarkus:dev
```

```
 curl http://127.0.0.1:8080/hello/hello-bug      
```


Calling this uri, we have a java.lang.ClassCastException: **l_ClientProxy cannot be cast to** 

```
Caused by: java.lang.ClassCastException: org.ecuadorjug.api.impl.HelloServiceImpl_ClientProxy cannot be cast to org.ecuadorjug.api.HelloService
        at org.ecuadorjug.app.HelloResource_Bean.create(Unknown Source)
        at org.ecuadorjug.app.HelloResource_Bean.create(Unknown Source)
        at io.quarkus.arc.AbstractSharedContext.createInstanceHandle(AbstractSharedContext.java:86)
        at io.quarkus.arc.AbstractSharedContext.lambda$new$0(AbstractSharedContext.java:33)
```


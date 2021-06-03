## Spring Cloud学习

### 服务注册和发现（eureka，zookeeper，consul)
- 创建项目 payment8001
- 创建项目 order80
  - RestTemplate 使用
- 创建项目 eureka-server7001
  - 引入eureka
  - eureka-server7001为注册中心
  - payment8001 order80注册为客户端
- 创建项目 eureka-server7002
  - eureka集群 负载均衡（@LoadBalanced）和故障容错 （互相注册，相互守望）
  - 支付微服务集群配置
  - 自我保护 不会轻易删除服务
- 创建项目 payment8003
  - Zookeeper 代替 Eureka
  - Zookeeper在服务器上 节点是临时性的，不像eureka
- 创建项目 zookeeper_order80
  - 创建服务消费者
- 创建项目 payment8005
  - 服务提供者，步骤和zookeeper一样
- 创建项目 consul_order80
  - 创建服务消费者，步骤和zookeeper一样
### 服务调用
- 使用Ribbon
  - spring-cloud-starter-netflix-eureka-client带有ribbon的依赖包
  - 负载均衡的规则（7种）
  - 轮询的原理： reset接口第几次请求数 % 服务器集群的总数量 = 实际调用服务器位置下标，每次服务重启后rest接口计数从1开始。
- 使用Feign
  - 是一个声明式的web服务客户端，让编写web服务客户端变得非常容易，只需创建一个接口并在接口上添加注解即可
  - 创建openfeign_order80服务
  - 将微服务变成一个接口 @FeignClient(value="微服务名称")
  - 自带负载均衡机制
  - 超时控制
    - 可以设置超时控制时间 yml配置
  - 日志增强
    - 提供日志打印
### 服务降级
- 使用Hystrix
  - 服务降级（服务调用失败的处理方法）、服务熔断（达到最大服务访问后，直接拒绝访问，然后调用服务降级方法）、接近实时的监控
  - 服务降级
    - 服务降级 客户端和服务端都可以做
    - 全局服务降级（@DefaultProperties(defaultFallback = "")）  通用和定制
  -服务熔断
    - 正常 -> 多次错误 -> 关闭 -> 正确率上升 -> 恢复
  - 服务监控hystrixDashboard
    - 监控服务的运行情况

### 服务网关
- zuul路由网关
- Gateway新一代网关

## restful 调试
> http://localhost:8001/payment/get/1
>
> http://eureka7001.com:7001/
>
> http://localhost/consumer/payment/get/1
>
> http://localhost:8003/payment/zk
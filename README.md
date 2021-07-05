## Spring Cloud学习
![image-20210609114259282](image-20210609114259282.png)

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
    - 路由（构成网关的基本模块，它由ID，目标URI，一系列的断言和过滤器组成），断言，过滤
    - 工作流程：客户端想gateway发出请求，然后在gateway handler mapping中找到与请求相匹配的路由，将其发送到gateway web handler.
               handler再通过指定的过滤器来将请求发送到我们实际的服务执行业务逻辑，然后返回。
               过滤器之间用虚线分开是因为过滤器可能会再发送请求之前（pre）或之后（post）执行业务逻辑
  - 创建项目gateway9527
    - 需要移除spring-boot-starter-web依赖
    - 主要是配置application.yml
    - 路由配置的两种方式（yml文件或者代码配置）
    - 动态路由配置
    - postman(图形化请求) curl(命令请求方式 curl http://localhost:9527/payment/lb --cookie [cookie] -H [请求头])
    - 断言类型，包含时间 cookie 请求头 请求方法等
    - 

### 服务配置
  - 每个微服务都需要一个配置文件，所以建立一个config server来管理配置
  - SpringCloud Config分为服务端和客户端两部分，与GitHub整合
  - bootstrap.yml
  - 创建项目config-center3344 Config配置总控中心搭建
  - 创建项目config-client3355 Config配置客户端搭建
  - Config动态刷新
    - 需要手动执行 curl -X POST "http://localhost:3355/actuator/refresh"

### 消息总线
  - SpringCloud Bus 支持两种消息代理 RabbitMQ 和 Kafka
  - 消息总线RabbitMQ实现动态刷新配置
    - 3344 3355 3366修改
    - 广播通知
      - 刷新服务端 curl -X POST "http://localhost:3344/actuator/bus-refresh"
    - 定点通知
      - 指定具体某个实例生效而不是全部（实例，只通知3355，不通知3366）
      - curl -X POST "http://localhost:3344/actuator/bus-refresh/config-client:3355"
  - Cloud Stream
    - 屏蔽底层消息中间减的差异，降低切换成本，统一消息的编程模型
    - 通过定义绑定器Binder作为中间层，实现了应用程序与消息中间件细节之间的隔离
      - Input对应消费者
      - Output对应生产者
    - stream_provider8801 消息发送者
    - stream_consumer8802 stream_consumer8803 消息消费者
      - 重复消费 -->消息分组（不同组是可以全面消费的，同一组内会发生竞争关系，只有其中一个可以消费）
        - yml配置组名
      - 持久化（消息丢失）
        - 删除8802分组后，关闭8802，8801发送消息后8802重启接收不到消息
        - 只关闭8803，8801发送消息后8803重启可以接收消息

### 分布式请求链路跟踪
  - 搭建zipkin server
    - java -jar zipkin-server-2.23.2-exec.jar
    - http://localhost:9411/zipkin/
  - 链路监控

### spring cloud alibaba
  - nacos服务注册和发现
    - windows安装nacos报错处理 https://www.cnblogs.com/rookiemzl/p/13814919.html
    - alibaba_payment9001 alibaba_payment9002服务提供者
    - alibaba_order83服务调用者
    - 支持AP和CP模式切换
  - nacos配置中心
    - @RefreshScope
    - 支持动态刷新
    - 加载配置 DataID方案 Group方案 Namespace方案
  - nacos集群和持久化配置
    - 集群
    - 持久化 mysql
  - Sentinel服务熔断和降级
    - 单独一个组件，可以独立出来；直接界面话的细粒度统一配置
    - 下载安装 启动：java -jar sentinel-dashboard-1.8.1.jar
              账号和密码：sentinel
    - 懒加载
    - 流控规则 
      - QPS（每秒的请求数量）和线程数
      - 关联：当与A关联的资源B达到阈值后，就限流A自己 **POSTMAN可以做压测**
      - 预热 刚开始阈值为设定阈值/3（3为默认设置），但是超过预热时长后，阈值变为设定阈值
      - 排队等待 请求匀速通过
    - 降级规则
      - RT（平均响应时间） 时间窗口内，请求超过平均响应时间，服务不可用
      - 异常比例  时间窗口内请求异常比例
      - 异常数  时间窗口内请求异常数
    - 热点key
      - @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
      - 参数例外项 参数等于某个值阈值可以变化
    - 系统规则
      - Load（只对linux和Unix的系统起作用） RT 线程数 入口QPS CPU使用率
    - SentinelResource配置
      - 按资源名称配置
      - 按url配置
      - 自定义限流处理配置
    - 服务熔断
    @SentinelResource(value="函数名"，fallback = "", blockHandler="")
      - fallback配置运行异常
      - blockHandler配置违反规则的异常
      - exceptionToIgnore忽略的异常
    - 整合OpenFeign报错 版本问题 未解决！！！
    - 规则持久化
    

## restful 调试
> http://localhost:8001/payment/get/1
> eureka测试
> http://eureka7001.com:7001/
> 消费端测试
> http://localhost/consumer/payment/get/1
> zookeeper测试
> http://localhost:8003/payment/zk
> 网关过滤器测试
> http://localhost:9527/payment/lb?username=22
> 服务配置
> http://localhost:3344/master/config-dev.yml
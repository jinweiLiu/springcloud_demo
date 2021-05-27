## Spring Cloud学习
- 创建项目 payment8001
- 创建项目 order80
  - RestTemplate 使用
- 创建项目 eureka-server7001
  - 引入eureka
  - eureka-server7001为注册中心
  - payment8001 order80注册为客户端
- 创建项目 eureka-server7002
  - eureka集群 负载均衡和故障容错 （互相注册，相互守望）
  - 支付微服务集群配置

## restful 调试
> http://localhost:8001/payment/get/1
>
> http://eureka7001.com:7001/
>
> http://localhost/consumer/payment/get/1
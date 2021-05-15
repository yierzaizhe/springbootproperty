package com.ylz.alipay.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@Data
public class AliPayConfig {
    public  String app_id = "2021000117659140";//在后台获取（必须配置）

    public  String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCoIMvM33q+mXJZq5w0eyym7BVxaUk8M99Nvd6t2gw6u1fWOKV3xw1OkYJ2FcHpA+UDywJmYR3ux/eE56jqZRAlvMkhlyO4XN7ZtFP5CPwfZfw+6pEnNAI820SnGipq1dTc7UWo2Dr5n17mqOGkSoTCE6gMTq54wd13iM79H8vNJz8v9plDIJPnWO351V3xeL4wbo1U4tez/0Qh8uCpVjH/eGI1BrCb+/6qxz6xkcepavE8af6mFTJvrUpAYh/8NiaWOrMuuhxBIVhwNaOK5uttY4PyEDEo+VhJFT3T2XOQYq/c+35XdAeejHDiBrRfgxWT2ROz/Sk7+W3C/n2qrZWvAgMBAAECggEAc+9pmbThZ11YxQzZgjcN620QvEvl/GWormsniqq5lCZrw4T8VUQloPAmDleih5Pv8FWlKgP+KBIsVLGKgVd/cwZji441S4R/B5CZ9ziC4wPolVobDfWHNX/eL3yTk2gwiMf1KSEySb/kKRp7vlbEhTAYyaPYG91TYoIsec/bMXOjPW4B+b6vE/mYvKRLILJkIUImJZnk32ij/EO8ddc8S/En/cn+vuxOV6CZWjzMmPTm4Db8froB+boU8c9ygrvC/UxZV3amAt4nBpE7Ggkld+ULtOkAOh58amPptgE3CAwwiuudj5RcbuHYJOx/QqKuZ+CHsbOMDdk5vFBmqQDrwQKBgQD8OLfNerJIt+U3pL2nalkmdfqmGnopTYay/N9h4Awuk7GhyCApP7N0Qt6QShfn9h4K5Aqa1VsAIwjW5F9cTbkrvVt579H4B4MNBFsN2Xxuul06ACmNiq8vaHQKbfv8IRed7zvpsgIFdLx6+BcLFQMDlT3z+eJWMglJAVrHmf8wqwKBgQCqpZNcwX5U3ki5bInqQJ9/U6LySX/es52puX5OGyhI66HXOegibsF2wtKD7ObqOhEgaeCcBbZXKrjCsFJ/yIKoUUj1KChHynnB/brxiJsdqX5cSZwEWqM3pWXEFi4O7MKGOBdbwR+ksyKI+hacYV/vIXYaDLNgbByaR378qYpXDQKBgB8J18yomcILJ2EWm+Q7WSc7g5HnVjs4+CeUqOTBjp44vLZdC2AoH6xiAkOdap24EvBiKc/uC5nTteXECtwwEGaKkfRKh/tO6VizvYTRy9dQheJ6XKK3e2GJKCaUbFsVs3NTGnti1Y7YU82KNEO/3TQWrJ4WlTzoQW5DhWlk0RurAoGAQt5pFXLZH0aPduGaVYoHNiy+8HRuwATgZk7CuLaZnaGAJMeTOFLwSqpjNcESZ3xjk7EJi/oNDrE1/0x6t4oJ8Q0RKsgWqLMl7SeRJu6h4gi19FH/6hDeXoLYkvMiD0UloNTACntt++saQFTHoXXi0uDo16JcdA9JUgdsMnP5Os0CgYEAzQ7Q9AArGFJTdHeTfUjIGn5ko6TDYnLl1SXw/xuGKDautQJZvHxz4Dr9EBCoKcX1wvRTmcSMYFxIUeREigzkco/6ddsd9zHNqSDpv1e0rpK2nt9iPaXwAEvjrMYFFYxShcN4cfHouBXKpPigaVbPaYeeITE1nLolIBk/fOU3ues=";//教程查看获取方式（必须配置）

    public  String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqCDLzN96vplyWaucNHsspuwVcWlJPDPfTb3erdoMOrtX1jild8cNTpGCdhXB6QPlA8sCZmEd7sf3hOeo6mUQJbzJIZcjuFze2bRT+Qj8H2X8PuqRJzQCPNtEpxoqatXU3O1FqNg6+Z9e5qjhpEqEwhOoDE6ueMHdd4jO/R/LzSc/L/aZQyCT51jt+dVd8Xi+MG6NVOLXs/9EIfLgqVYx/3hiNQawm/v+qsc+sZHHqWrxPGn+phUyb61KQGIf/DYmljqzLrocQSFYcDWjiubrbWOD8hAxKPlYSRU909lzkGKv3Pt+V3QHnoxw4ga0X4MVk9kTs/0pO/ltwv59qq2VrwIDAQAB";//教程查看获取方式（必须配置）

    public  String notify_url = "http://localhost:8080/*";

    public  String return_url = "http://localhost:8080/parkingLog";

    public  String sign_type = "RSA2";

    public  String charset = "utf-8";

    public  String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";//注意：沙箱测试环境，正式环境为：https://openapi.alipay.com/gateway.do

}

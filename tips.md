# 项目注意事项
## 1.对于用户数据的存储
用户数据将会通过redis存储，这样可以不使用token来判断用户是否登录，
但是要定时设置一个时间值**t**来确保用户过长时间没登录来保证安全性
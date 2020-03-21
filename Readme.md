# 注意


1. Jredis key要设置 Serialize 否则和PHP不通用   



# 类数据结构体   

1. API返回参数   

ApiResponse(返回数据结构定义),ApiResponseCode(返回码定义),

2. 异常

   2.1 ParameterRequiredException   
        参数必须异常
        
   2.2 CryptException   
        加解密异常



# 工具包

1. 加解密包   

AesUtils,  Base64Utils, DesCbcUtils,    
DesCbcUtils, JwtTokenUtil, Md5Utils, RsaUtils   

2. 字符串，数值 

DecimalUtils, RandomStringUtils   

3. 日期工具包   

DateUtils   



# jwt-demo
### 一 JWT简介
    1 Json Web Token 简称JWT，JWT将用户信息保存在通过base64编码然后加密到token传给客户端。
    token由客户端保存，服务器不保存token信息，用户每次访问服务器，都带着token过来，然后
    服务器通过制定的签名解析token，验证token是否正确，同时获取用户登录信息。
    2 由于token保存在用户端，而且base64是可以可逆解析的，所以token中不建议存用户敏感信息，
    如密码这些存在安全问题的信息。
    3 JWT的的token不会自动过期，一旦获取token设定了过期时间，在过期之前使用该token到任何
    机器都可以正常登陆服务器，所以如果需要人为干预过期时间，那么可以考虑在JWT的基础上加入
    redis缓存token,服务器通过获取redis的token判断用户登录是否过期。
### 二 JWT结构组成部分
    由三部分生成token 
    3部分之间用“.”号做分隔。例如eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c 

* 头部（header)
    ###### Header 部分是一个 JSON 对象，描述 JWT 的元数据，通常是下面的样子。
    <pre><code>
    {
      "alg": "HS256",
      "typ": "JWT"
    }
    </code></pre>
* 其为载荷（payload)
     ###### payload一般用于存放用户信息以及token过期时间，下面是官方字段。
    <pre><code>
    iss (issuer)：签发人
    exp (expiration time)：过期时间
    sub (subject)：主题
    aud (audience)：受众
    nbf (Not Before)：生效时间
    iat (Issued At)：签发时间
    jti (JWT ID)：编号
    </code></pre>
    ###### 除了官方原有字段，自己可以自定义加入其他的用户信息字段。如下所示：
    <pre><code>
    {
      "sex": "1",
      "name": "John Doe",
      "admin": true
    }
    </code></pre>
* 签证（signature) 
    ###### Signature 部分是对前两部分的签名，防止数据篡改。<BR/>首先，需要指定一个密钥（secret）。这个密钥只有服务器才知道，不能泄露给用户。<BR/>然后，使用 Header 里面指定的签名算法（默认是 HMAC SHA256），按照下面的公式产生签名。
    <pre><code>
     HMACSHA256(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
    </code></pre>
    ###### 算出签名以后，把 Header、Payload、Signature 三个部分拼成一个字符串，每个部分之间用"点"（.）分隔，就可以返回给用户。
### 三 JWT 的几个特点
* JWT 默认是不加密，但也是可以加密的。生成原始 Token 以后，可以用密钥再加密一次。

* WT 不加密的情况下，不能将秘密数据写入 JWT。

* JWT 不仅可以用于认证，也可以用于交换信息。有效使用 JWT，可以降低服务器查询数据库的次数。

* JWT 的最大缺点是，由于服务器不保存 session 状态，因此无法在使用过程中废止某个 token，或者更改 token 的权限。也就是说，一旦 JWT 签发了，在到期之前就会始终有效，除非服务器部署额外的逻辑。

* JWT 本身包含了认证信息，一旦泄露，任何人都可以获得该令牌的所有权限。为了减少盗用，JWT 的有效期应该设置得比较短。对于一些比较重要的权限，使用时应该再次对用户进行认证。

* 为了减少盗用，JWT 不应该使用 HTTP 协议明码传输，要使用 HTTPS 协议传输。
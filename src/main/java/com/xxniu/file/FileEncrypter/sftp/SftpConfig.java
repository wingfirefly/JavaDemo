package com.xxniu.file.FileEncrypter.sftp;

public class SftpConfig
{
    public static final int SFTP_DEFAULT_PORT = 22;

    /* sftp类型 密码连接 */
    public static final String SFTP_TYPE_PASSWORD = "1";
    /* sftp类型 密钥连接 */
    public static final String SFTP_TYPE_PRIVATEKEY = "2";
    public String host;
    public String port;
    public String username;
    public String password;
    public String privateKey;
    public String location;
    public String type;

    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public String getPort()
    {
        return port;
    }

    public void setPort(String port)
    {
        this.port = port;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPrivateKey()
    {
        return privateKey;
    }

    public void setPrivateKey(String privateKey)
    {
        this.privateKey = privateKey;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "SftpConfig [host=" + host + ", port=" + port + ", username=" + username + ", password=" + password + ", privateKey=" + privateKey + ", location=" + location + ", type=" + type + "]";
    }

}
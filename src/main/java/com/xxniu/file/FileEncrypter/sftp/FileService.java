package com.xxniu.file.FileEncrypter.sftp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.jcraft.jsch.ChannelSftp;

public class FileService
{

    public static boolean getFileByPassword(String sourcePath, String remotePath, String host, String port, String userName, String password)
    {
        SftpConfig sftpConfig = new SftpConfig();
        sftpConfig.setHost(host);
        sftpConfig.setPort(port);
        sftpConfig.setUsername(userName);
        sftpConfig.setPassword(password);
        sftpConfig.setType(SftpConfig.SFTP_TYPE_PASSWORD);

        SftpChannel sftpUtil = null;
        InputStream instream = null;
        OutputStream out = null;
        try
        {
            sftpUtil = new SftpChannel();
            ChannelSftp sftpChannel = sftpUtil.getChannel(sftpConfig, 5 * 60 * 1000);

            // 以下代码实现从本地上传一个文件到服务器，如果要实现下载，对换以下流就可以了
            out = new FileOutputStream(sourcePath);
            instream = sftpChannel.get(remotePath);

            byte[] buff = new byte[1024 * 2];
            int read;
            if (instream != null)
            {
                do
                {
                    read = instream.read(buff, 0, buff.length);
                    if (read > 0)
                    {
                        out.write(buff, 0, read);
                    }
                    out.flush();
                } while (read >= 0);
            }
            return true;
        } catch (Exception e)
        {
            return false;
        }
        finally
        {
            try
            {
                if (out != null)
                {
                    out.close();
                }
                if (instream != null)
                {
                    instream.close();
                }
                if (sftpUtil != null)
                {
                    sftpUtil.closeChannel();
                }
            } catch (Exception e1)
            {
            }
        }

    }

    public static boolean putFileByPassword(String sourcePath, String remotePath, String host, String port, String userName, String password)
    {
        SftpConfig sftpConfig = new SftpConfig();
        sftpConfig.setHost(host);
        sftpConfig.setPort(port);
        sftpConfig.setUsername(userName);
        sftpConfig.setPassword(password);
        sftpConfig.setType(SftpConfig.SFTP_TYPE_PASSWORD);

        SftpChannel sftpUtil = null;
        InputStream instream = null;
        OutputStream out = null;
        try
        {
            sftpUtil = new SftpChannel();
            ChannelSftp sftpChannel = sftpUtil.getChannel(sftpConfig, 5 * 60 * 1000);

            out = sftpChannel.put(remotePath);
            instream = new FileInputStream(sourcePath);
            byte[] buff = new byte[1024 * 2];
            int read;
            if (instream != null)
            {
                do
                {
                    read = instream.read(buff, 0, buff.length);
                    if (read > 0)
                    {
                        out.write(buff, 0, read);
                    }
                    out.flush();
                } while (read >= 0);
            }
            return true;
        } catch (Exception e)
        {
            return false;
        }
        finally
        {
            try
            {
                if (out != null)
                {
                    out.close();
                }
                if (instream != null)
                {
                    instream.close();
                }
                if (sftpUtil != null)
                {
                    sftpUtil.closeChannel();
                }
            } catch (Exception e1)
            {
            }
        }

    }

    public static boolean getFileByPrivateKey(String sourcePath, String remotePath, String host, String port, String userName)
    {
        SftpConfig sftpConfig = new SftpConfig();
        sftpConfig.setHost(host);
        sftpConfig.setPort(port);
        sftpConfig.setUsername(userName);
        sftpConfig.setType(SftpConfig.SFTP_TYPE_PRIVATEKEY);

        SftpChannel sftpUtil = null;
        InputStream instream = null;
        OutputStream out = null;
        try
        {
            sftpUtil = new SftpChannel();
            ChannelSftp sftpChannel = sftpUtil.getChannel(sftpConfig, 5 * 60 * 1000);

            // 以下代码实现从本地上传一个文件到服务器，如果要实现下载，对换以下流就可以了
            out = new FileOutputStream(sourcePath);
            instream = sftpChannel.get(remotePath);

            byte[] buff = new byte[1024 * 2];
            int read;
            if (instream != null)
            {
                do
                {
                    read = instream.read(buff, 0, buff.length);
                    if (read > 0)
                    {
                        out.write(buff, 0, read);
                    }
                    out.flush();
                } while (read >= 0);
            }
            return true;
        } catch (Exception e)
        {
            return false;
        }
        finally
        {
            try
            {
                if (out != null)
                {
                    out.close();
                }
                if (instream != null)
                {
                    instream.close();
                }
                if (sftpUtil != null)
                {
                    sftpUtil.closeChannel();
                }
            } catch (Exception e1)
            {
            }
        }

    }

    public static boolean putFileByPrivateKey(String sourcePath, String remotePath, String host, String port, String userName)
    {
        SftpConfig sftpConfig = new SftpConfig();
        sftpConfig.setHost(host);
        sftpConfig.setPort(port);
        sftpConfig.setUsername(userName);
        sftpConfig.setType(SftpConfig.SFTP_TYPE_PRIVATEKEY);

        SftpChannel sftpUtil = null;
        InputStream instream = null;
        OutputStream out = null;
        try
        {
            sftpUtil = new SftpChannel();
            ChannelSftp sftpChannel = sftpUtil.getChannel(sftpConfig, 5 * 60 * 1000);

            // 以下代码实现从本地上传一个文件到服务器，如果要实现下载，对换以下流就可以了
            out = sftpChannel.put(remotePath);
            instream = new FileInputStream(sourcePath);

            byte[] buff = new byte[1024 * 2];
            int read;
            if (instream != null)
            {
                do
                {
                    read = instream.read(buff, 0, buff.length);
                    if (read > 0)
                    {
                        out.write(buff, 0, read);
                    }
                    out.flush();
                } while (read >= 0);
            }
            return true;
        } catch (Exception e)
        {
            return false;
        }
        finally
        {
            try
            {
                if (out != null)
                {
                    out.close();
                }
                if (instream != null)
                {
                    instream.close();
                }
                if (sftpUtil != null)
                {
                    sftpUtil.closeChannel();
                }
            } catch (Exception e1)
            {
            }
        }

    }

}
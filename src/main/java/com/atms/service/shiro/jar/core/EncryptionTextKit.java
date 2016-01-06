package com.atms.service.shiro.jar.core;

import com.sun.crypto.provider.SunJCE;

import java.security.Security;

/**
 * Title:
 * Description:
 *
 * @author:eric
 * @date: 2015-12-16 15:51
 */
public class EncryptionTextKit
{
    private static String strDefaultKey = "_jfinal_secret_key";

    private javax.crypto.Cipher encryptCipher = null;

    private javax.crypto.Cipher decryptCipher = null;

    public EncryptionTextKit()
            throws Exception
    {
        this(strDefaultKey);
    }

    public EncryptionTextKit(String strKey)
            throws Exception
    {
        Security.addProvider(new SunJCE());
        java.security.Key key = getKey(strKey.getBytes());

        this.encryptCipher = javax.crypto.Cipher.getInstance("DES");
        this.encryptCipher.init(1, key);

        this.decryptCipher = javax.crypto.Cipher.getInstance("DES");
        this.decryptCipher.init(2, key);
    }

    public static String byteArr2HexStr(byte[] arrB)
            throws Exception
    {
        int iLen = arrB.length;

        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];

            while (intTmp < 0) {
                intTmp += 256;
            }

            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    public static byte[] hexStr2ByteArr(String strIn)
            throws Exception
    {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;

        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i += 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[(i / 2)] = ((byte)Integer.parseInt(strTmp, 16));
        }
        return arrOut;
    }

    public byte[] encrypt(byte[] arrB)
            throws Exception
    {
        return this.encryptCipher.doFinal(arrB);
    }

    public String encrypt(String strIn)
            throws Exception
    {
        return byteArr2HexStr(encrypt(strIn.getBytes()));
    }

    public byte[] decrypt(byte[] arrB)
            throws Exception
    {
        return this.decryptCipher.doFinal(arrB);
    }

    public String decrypt(String strIn)
            throws Exception
    {
        try
        {
            return new String(decrypt(hexStr2ByteArr(strIn)));
        } catch (Exception e) {}
        return "";
    }

    private java.security.Key getKey(byte[] arrBTmp)
            throws Exception
    {
        byte[] arrB = new byte[8];

        for (int i = 0; (i < arrBTmp.length) && (i < arrB.length); i++) {
            arrB[i] = arrBTmp[i];
        }

        java.security.Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");

        return key;
    }
}


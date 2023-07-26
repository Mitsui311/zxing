package com.google.zxing.common.reedsolomon;

import java.util.Arrays;

import com.google.zxing.ChecksumException;

//RS符号のテスト用ファイル
public class test {

    public static void main(String[]args)
    {

        //符号化したいシンボル列と符号語の誤り訂正可能数を指定
        int[] Data = {111 ,24 ,9 ,49 ,152 ,221};
        int Errornum = 2;
        int Two_Errornum = 2 * Errornum;

        System.out.println("data" + Arrays.toString(Data));

        //符号化
        int numData = Data.length;
        int[] ToEncode= new int[numData + Two_Errornum];
        for (int i = 0; i < numData; i++) {
        ToEncode[i] = Data[i];
        }
        new ReedSolomonEncoder(GenericGF.QR_CODE_FIELD_256).encode(ToEncode, Two_Errornum);
        System.out.println("encode" + Arrays.toString(ToEncode));


        //誤りを付与
        int[] ErrorINencode = new int[ToEncode.length];
        for (int i = 0; i < ErrorINencode.length; i++) {
        ErrorINencode[i] = ToEncode[i];
        }
        ErrorINencode[0] = 10;
        ErrorINencode[1] = 10;
        ErrorINencode[4] = 10;
        ErrorINencode[5] = 33;
        System.out.println("errorin" + Arrays.toString(ErrorINencode));

        int[] eraseposition = {0, 1, 4, 5};


        //復号
        ReedSolomonDecoder rsDecoder = new ReedSolomonDecoder(GenericGF.QR_CODE_FIELD_256);
        try {
            // int errorsCorrected = rsDecoder.decodeWithECCount(ErrorINencode, Two_Errornum);
            rsDecoder.erasedecode(ErrorINencode, eraseposition, Two_Errornum);
        } catch (ReedSolomonException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("decode" + Arrays.toString(ErrorINencode));
        


    }
}
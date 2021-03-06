/**
 * @version $Id: IDataProvider.java 1839 2014-04-16 02:33:51Z yukihiro-kinjyo $
 * 
 * 2011/08/29 21:30:11
 * @author imai
 * 
 * Copyright 2011-2014 TIDAコンソーシアム All Rights Reserved.
 */
package com.tida_okinawa.corona.correction.controller;

import org.eclipse.jface.operation.IRunnableWithProgress;


/**
 * データプロバイダー
 * 
 * 問い合わせ情報など量が多いデータを１件ずつ処理するのに使う
 * 
 * @author imai
 * 
 * @param <T>
 *            入力データ
 */
public interface IDataProvider<T> extends IRunnableWithProgress {
    // TODO UIから丸コピしてきたクラス
    /**
     * データの総数
     * 
     * @return -1 不明
     */
    int total();


    /**
     * 次のデータを取り出す
     * 
     * @return データ null=データ終わり
     */
    T next();
}

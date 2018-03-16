package com.fitem.games.common.baserx;


/**
 * des:对服务器返回数据成功和失败处理
 * Created by xsf
 * on 2016.09.9:59
 */

import com.fitem.games.common.helper.LogHelper;
import com.fitem.games.common.basebean.BaseRespose;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**************使用例子******************/
/*_apiService.login(mobile, verifyCode)
        .compose(RxSchedulersHelper.io_main())
        .compose(RxResultHelper.handleResult())
        .//省略*/

public class RxHelper {
    /**
     * 对服务器返回数据进行预处理
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<BaseRespose<T>, T> handleResult() {
        return new ObservableTransformer<BaseRespose<T>, T>() {

            @Override
            public ObservableSource<T> apply(Observable<BaseRespose<T>> upstream) {
                return upstream.flatMap(new Function<BaseRespose<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(BaseRespose<T> result) throws Exception {
                        LogHelper.logd("result from api : " + result);
                        if (result.ret == 1) {
                            //判断data数据是否为空
                            if (result.data == null) {
                                return Observable.error(new ServerException("null"));
                            }
                            return createData(result.data);
                        } else if (result.errorNo == -998) {
                            //判断ukey是否变动
                            return Observable.error(new ServerException("relogin"));
                        } else if (result.errorNo == -6){
                            // 超过获取金币限制
                            return Observable.error(new ServerException("over_limit"));
                        } else {
                            return Observable.error(new ServerException(result.msg));
                        }
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };

    }

    /**
     * 创建成功的数据
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new ObservableOnSubscribe<T>() {

            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(data);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        });

    }
}

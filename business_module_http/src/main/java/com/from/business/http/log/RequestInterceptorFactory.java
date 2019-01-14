package com.from.business.http.log;

import com.from.business.http.HttpHandler;
import com.from.business.http.dagger.Factory;
import com.from.business.http.dagger.Provider;

public final class RequestInterceptorFactory implements Factory<RequestInterceptor> {
    private final Provider<HttpHandler> mHandlerProvider;
    private final Provider<FormatPrinter> mPrinterProvider;
    private final Provider<RequestInterceptor.Level> printLevelProvider;

    public RequestInterceptorFactory(
        Provider<HttpHandler> mHandlerProvider,
        Provider<FormatPrinter> mPrinterProvider,
        Provider<RequestInterceptor.Level> printLevelProvider) {
        this.mHandlerProvider = mHandlerProvider;
        this.mPrinterProvider = mPrinterProvider;
        this.printLevelProvider = printLevelProvider;
    }

    @Override
    public RequestInterceptor get() {
        return provideInstance(mHandlerProvider, mPrinterProvider, printLevelProvider);
    }

    public static RequestInterceptor provideInstance(
        Provider<HttpHandler> mHandlerProvider,
        Provider<FormatPrinter> mPrinterProvider,
        Provider<RequestInterceptor.Level> printLevelProvider) {
        RequestInterceptor instance = new RequestInterceptor();
        RequestInterceptorMembersInjector.injectMHandler(instance, mHandlerProvider.get());
        RequestInterceptorMembersInjector.injectMPrinter(instance, mPrinterProvider.get());
        RequestInterceptorMembersInjector.injectPrintLevel(instance, printLevelProvider.get());
        return instance;
    }

    public static RequestInterceptorFactory create(
        Provider<HttpHandler> mHandlerProvider,
        Provider<FormatPrinter> mPrinterProvider,
        Provider<RequestInterceptor.Level> printLevelProvider) {
        return new RequestInterceptorFactory(mHandlerProvider, mPrinterProvider, printLevelProvider);
    }

    public static RequestInterceptor newRequestInterceptor() {
        return new RequestInterceptor();
    }
}

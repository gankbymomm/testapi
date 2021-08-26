package vn.vnpay.utils;

import com.google.inject.AbstractModule;
import vn.vnpay.service.MerchantService;
import vn.vnpay.service.impl.MerchantServiceImpl;

public class MerchantModuleUtils extends AbstractModule {
    @Override
    protected void configure() {
        bind(MerchantService.class).to(MerchantServiceImpl.class);
    }
}

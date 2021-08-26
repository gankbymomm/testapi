package vn.vnpay.controller;

import com.google.inject.Guice;
import com.google.inject.Injector;
import vn.vnpay.utils.MerchantModuleUtils;

public class BaseController {

    Injector injector = Guice.createInjector(new MerchantModuleUtils());
    InjectMerchant merchant = injector.getInstance(InjectMerchant.class);

}

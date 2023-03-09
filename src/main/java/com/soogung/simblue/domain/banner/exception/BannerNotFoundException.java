package com.soogung.simblue.domain.banner.exception;

import com.soogung.simblue.domain.banner.exception.error.BannerErrorProperty;
import com.soogung.simblue.global.error.exception.SimblueException;

public class BannerNotFoundException extends SimblueException {

    public final static BannerNotFoundException EXCEPTION = new BannerNotFoundException();

    private BannerNotFoundException() {
        super(BannerErrorProperty.BANNER_NOT_FOUND);
    }
}

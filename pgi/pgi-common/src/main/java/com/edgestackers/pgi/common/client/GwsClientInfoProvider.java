package com.edgestackers.pgi.common.client;

import com.amtote.gws.GpHeader;

public record GwsClientInfoProvider(GpHeader gpHeader,
                                    GwsAccountInfo masterAccountInfo,
                                    GwsAccountInfo rebateAccountInfo) {
}

//
//  GetAccountAssetRequest.m
//  pocketEOS
//
//  Created by oraclechain on 2018/1/23.
//  Copyright © 2018年 oraclechain. All rights reserved.
//

#import "GetAccountAssetRequest.h"

@implementation GetAccountAssetRequest

-(NSString *)requestUrlPath{
//    return [NSString stringWithFormat:@"%@/get_account_asset", REQUEST_BLOCKCHAIN_BASEURL];
    return @"/get_account_asset";
}

-(id)parameters{
    return @{@"account_name" : VALIDATE_STRING(self.account_name) };
}
@end

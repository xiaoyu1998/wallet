//
//  GetAccountRequest.m
//  pocketEOS
//
//  Created by oraclechain on 2018/2/6.
//  Copyright © 2018年 oraclechain. All rights reserved.
//

#import "GetAccountRequest.h"

@implementation GetAccountRequest
-(NSString *)requestUrlPath{
//    return [NSString stringWithFormat:@"%@/get_account", REQUEST_BLOCKCHAIN_BASEURL];
    return @"/get_account_app";
}

-(NSDictionary *)parameters{
    // 交易JSON序列化
    NSMutableDictionary *params = [NSMutableDictionary dictionary];
    [params setObject: VALIDATE_STRING(self.account_name) forKey:@"account_name"];
    return params;
}

//-(id)parameters{
//    return @{@"account_name" : VALIDATE_STRING(self.account_name) };
//}

@end


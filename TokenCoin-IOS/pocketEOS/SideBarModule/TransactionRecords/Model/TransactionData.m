//
//  TransactionRecord.m
//  pocketEOS
//
//  Created by oraclechain on 2018/2/7.
//  Copyright © 2018年 oraclechain. All rights reserved.
//

#import "TransactionData.h"

@implementation TransactionData
+(NSDictionary *)mj_replacedKeyFromPropertyName{
    return @{
             @"from" : @"from",
             @"to" : @"to",
             @"quantity" : @"quantity",
             @"memo" : @"memo",
             };
}
@end


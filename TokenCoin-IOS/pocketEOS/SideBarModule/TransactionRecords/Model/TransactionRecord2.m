//
//  TransactionRecord.m
//  pocketEOS
//
//  Created by oraclechain on 2018/2/7.
//  Copyright © 2018年 oraclechain. All rights reserved.
//

#import "TransactionRecord2.h"

@implementation TransactionRecord2
+(NSDictionary *)mj_replacedKeyFromPropertyName{
    return @{
             @"transcation_id" : @"transcation_id",
             @"from"       : @"data.from",
             @"to"         : @"data.to",
             @"quantity"   : @"data.quantity",
             @"memo"       : @"data.memo",
             //@"data" : @"data",
             @"block_time" : @"block_time",
             @"block_num"  : @"block_num",
             @"seq_num"    : @"seq_num"
             };
}
@end



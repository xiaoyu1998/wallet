//
//  TransactionsResult.m
//  pocketEOS
//
//  Created by oraclechain on 2018/2/7.
//  Copyright © 2018年 oraclechain. All rights reserved.
//

#import "TransactionsResult2.h"

@implementation TransactionsResult2
+(NSDictionary *)mj_objectClassInArray{
    return @{ @"actions" : @"actions",
              @"last_irreversible_block" : @"last_irreversible_block" };
}
@end

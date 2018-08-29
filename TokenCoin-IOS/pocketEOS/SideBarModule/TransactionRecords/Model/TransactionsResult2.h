//
//  TransactionsResult.h
//  pocketEOS
//
//  Created by oraclechain on 2018/2/7.
//  Copyright © 2018年 oraclechain. All rights reserved.
//

#import <Foundation/Foundation.h>

// 交易记录 Model
@interface TransactionsResult2 : NSObject
@property(nonatomic, strong) NSMutableArray *actions;
@property(nonatomic, strong) NSString *last_irreversible_block;
@end

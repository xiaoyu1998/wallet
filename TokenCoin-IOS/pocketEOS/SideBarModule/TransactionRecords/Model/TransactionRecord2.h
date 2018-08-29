//
//  TransactionRecord.h
//  pocketEOS
//
//  Created by oraclechain on 2018/2/7.
//  Copyright © 2018年 oraclechain. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "TransactionData.h"

@interface TransactionRecord2 : NSObject

/**
 接受的返回数据type不止transfer，所以必须判断transactionType == transfer
 */
@property(nonatomic, copy) NSString * transcation_id;

/**
 amount
 */
@property(nonatomic , copy) NSString *amount;

/**
 EOS .. OCT..
 */
@property(nonatomic, copy) NSString *assestsType;
/**
 amount + assestsType
 */

//@property(nonatomic, copy) TransactionData *data;

/**
 amount + assestsType
 */

@property(nonatomic, copy) NSString *quantity;

/**
 付款方
 */
@property(nonatomic, copy) NSString *from;
/**
 
 收款方
 */
@property(nonatomic, copy) NSString *to;


/**
  memo
 */
@property(nonatomic, copy) NSString *memo;


/**
 过期时间
 */
@property(nonatomic, copy) NSString *block_time;

/**
 block_num
 */
@property(nonatomic, copy) NSString *block_num;
@property(nonatomic, copy) NSString *seq_num;

@end

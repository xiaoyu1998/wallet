//
//  TransactionRecord.h
//  pocketEOS
//
//  Created by oraclechain on 2018/2/7.
//  Copyright © 2018年 oraclechain. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface TransactionData : NSObject


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


@end

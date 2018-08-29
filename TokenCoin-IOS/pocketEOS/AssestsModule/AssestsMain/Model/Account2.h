//
//  Account.h
//  pocketEOS
//
//  Created by oraclechain on 2018/1/23.
//  Copyright © 2018年 oraclechain. All rights reserved.
//

#import <Foundation/Foundation.h>


/**
 eos账号, 
 */
@interface Account2 : NSObject

/**
 eos账号名
 */
@property(nonatomic, copy) NSString *account_name;

/**
 eos账号头像
 */
@property(nonatomic, copy) NSString *account_icon;


@property(nonatomic, strong) NSMutableArray *account_assets;


@end

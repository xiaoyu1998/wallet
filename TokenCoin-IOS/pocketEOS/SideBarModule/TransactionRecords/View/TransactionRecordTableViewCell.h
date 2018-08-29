//
//  TransactionRecordTableViewCell.h
//  pocketEOS
//
//  Created by oraclechain on 2018/2/7.
//  Copyright © 2018年 oraclechain. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "TransactionRecord2.h"

@interface TransactionRecordTableViewCell : BaseTableViewCell
@property(nonatomic , copy) NSString *currentAccountName;
@property(nonatomic, strong) TransactionRecord2 *model;
@end

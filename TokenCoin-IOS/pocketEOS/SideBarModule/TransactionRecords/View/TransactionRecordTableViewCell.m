//
//  TransactionRecordTableViewCell.m
//  pocketEOS
//
//  Created by oraclechain on 2018/2/7.
//  Copyright © 2018年 oraclechain. All rights reserved.
//

#import "TransactionRecordTableViewCell.h"

@interface TransactionRecordTableViewCell()
@property(nonatomic, strong) UILabel *titleLabel;
@property(nonatomic, strong) UILabel *amountLabel;
@property(nonatomic, strong) UILabel *belongBlockLabel;
@property(nonatomic, strong) UILabel *resultLabel;

@end


@implementation TransactionRecordTableViewCell

- (UILabel *)titleLabel{
    if (!_titleLabel) {
        _titleLabel = [[UILabel alloc] init];
        _titleLabel.font = [UIFont systemFontOfSize:14];
        _titleLabel.textColor = HEXCOLOR(0x2A2A2A);
        _titleLabel.textAlignment = NSTextAlignmentLeft;
    }
    return _titleLabel;
}

- (UILabel *)amountLabel{
    if (!_amountLabel) {
        _amountLabel = [[UILabel alloc] init];
        _amountLabel.font = [UIFont systemFontOfSize:14];
        _amountLabel.textColor = HEXCOLOR(0x2A2A2A);
        _amountLabel.textAlignment = NSTextAlignmentRight;
    }
    return _amountLabel;
}

- (UILabel *)belongBlockLabel{
    if (!_belongBlockLabel) {
        _belongBlockLabel = [[UILabel alloc] init];
        _belongBlockLabel.font = [UIFont systemFontOfSize:14];
        _belongBlockLabel.textColor = HEXCOLOR(0xB0B0B0);
        _belongBlockLabel.textAlignment = NSTextAlignmentLeft;
    }
    return _belongBlockLabel;
}

- (UILabel *)resultLabel{
    if (!_resultLabel) {
        _resultLabel = [[UILabel alloc] init];
        _resultLabel.font = [UIFont systemFontOfSize:14];
        _resultLabel.textColor = HEXCOLOR(0xB0B0B0);
        _resultLabel.textAlignment = NSTextAlignmentRight;
    }
    return _resultLabel;
}



-(instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier{
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        [self.contentView addSubview:self.titleLabel];
        self.titleLabel.sd_layout.leftSpaceToView(self.contentView, MARGIN_20).topSpaceToView(self.contentView, 16).heightIs(20).widthIs(SCREEN_WIDTH - (2 * MARGIN_20) - 80);
        
        [self.contentView addSubview:self.amountLabel];
        self.amountLabel.sd_layout.rightSpaceToView(self.contentView, MARGIN_20).topSpaceToView(self.contentView, 16).heightIs(20);
        [self.amountLabel setSingleLineAutoResizeWithMaxWidth:SCREEN_WIDTH / 2];
        
        [self.contentView addSubview:self.belongBlockLabel];
        self.belongBlockLabel.sd_layout.leftSpaceToView(self.contentView, MARGIN_20).topSpaceToView(self.titleLabel, 1).heightIs(20);
        [self.belongBlockLabel setSingleLineAutoResizeWithMaxWidth:SCREEN_WIDTH];
        
        [self.contentView addSubview:self.resultLabel];
        self.resultLabel.sd_layout.rightSpaceToView(self.contentView, MARGIN_20).topSpaceToView(self.amountLabel, 1).heightIs(20);
        [self.resultLabel setSingleLineAutoResizeWithMaxWidth:SCREEN_WIDTH / 2];
    }
    return self;
}

- (void)setModel:(TransactionRecord2 *)model{
    // if ([model.from isEqualToString:@"oc.redpacket"] || [model.to isEqualToString:@"oc.redpacket"]) {
    //     // redpacket
    //     if ([model.from isEqualToString:@"oc.redpacket"]) {
    //         self.titleLabel.text = [NSString stringWithFormat: @"红包入账"];
    //     }else if ([model.to isEqualToString:@"oc.redpacket"]){
    //         self.titleLabel.text = [NSString stringWithFormat:@"发出红包"];
    //     }
    // }else{
        // transfer
    if ([self.currentAccountName isEqualToString:model.from]) {
        self.titleLabel.text = [NSString stringWithFormat:@"发送给%@" , model.to];
    }else if ([self.currentAccountName isEqualToString:model.to]){
        self.titleLabel.text = [NSString stringWithFormat:@"接受自%@" , model.from];
    }
    //}

    if ([self.currentAccountName isEqualToString:model.from]) {
        self.amountLabel.text = [NSString stringWithFormat:@"-%@",  model.quantity];
        self.amountLabel.textColor = HEXCOLOR(0x2A2A2A);
    }else if([self.currentAccountName isEqualToString:model.to]){
        self.amountLabel.text = [NSString stringWithFormat:@"+%@",  model.quantity];
        self.amountLabel.textColor = HEXCOLOR(0xE903C);
    }


    //self.belongBlockLabel.text = [NSString stringWithFormat:@"区块:%@",model.ref_block_num];
    self.belongBlockLabel.text = [NSString stringWithFormat:@"%@ %@",model.block_num, model.block_time];
    self.resultLabel.text = [NSString stringWithFormat:@"成功"];
}

@end

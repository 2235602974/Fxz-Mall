<!-- 商品详情页 -->
<template>
  <view class="container">
    <view class="carousel">
      <swiper indicator-dots circular="true" duration="400">
        <swiper-item class="swiper-item" v-for="(item, index) in goodsInfo.album" :key="index">
          <view class="image-wrapper">
            <image :src="item" class="loaded" mode="aspectFill"></image>
          </view>
        </swiper-item>
      </swiper>
    </view>

    <view class="introduce-section">
      <text class="title">{{ goodsInfo.name }}</text>
      <view class="price-box">
        <text class="price-tip">¥</text>
        <text class="price">{{ selectedSku.price / 100 }}</text>
        <text class="m-price" v-if="selectedSku.promotion">¥{{ selectedSku.originPrice /100 }}</text>
        <!-- <text class="coupon-tip">7折</text> -->
      </view>
      <view class="bot-row">
        <text>销量: {{goodsInfo.sales}}</text>
        <text>库存: {{selectedSku.stockNum}}</text>
        <text>浏览量: 768</text>
      </view>
    </view>
    <!--  分享 -->
    <view class="share-section" @click="share">
      <view class="share-icon">
        <text class="yticon icon-xingxing"></text>
        返
      </view>
      <text class="tit">该商品分享可领49减10红包</text>
      <text class="yticon icon-bangzhu1"></text>
      <view class="share-btn">
        立即分享
        <text class="yticon icon-you"></text>
      </view>
    </view>

    <view class="c-list">
      <view class="c-row b-b" @click="toggleSpec">
        <text class="tit">购买类型</text>
        <view class="con">
          <text class="selected-text" v-for="sItem in selectedSpecValues">
            {{ sItem.value }}
          </text>
        </view>
        <text class="yticon icon-you"></text>
      </view>
      <view class="c-row b-b" @click="toggleCoupon">
        <text class="tit">优惠券</text>
        <text class="con t-r red">领取优惠券</text>
        <text class="yticon icon-you"></text>
      </view>
      <view class="c-row b-b">
        <text class="tit">促销活动</text>
        <view class="con-list">
          <text>新人首单送20元无门槛代金券</text>
          <text>订单满50减10</text>
          <text>订单满100减30</text>
          <text>单笔购买满两件免邮费</text>
        </view>
      </view>
      <view class="c-row b-b">
        <text class="tit">服务</text>
        <view class="bz-list con">
          <text>7天无理由退换货 ·</text>
          <text>假一赔十 ·</text>
        </view>
      </view>
    </view>
	
	<!-- 评价 -->
    <view class="eva-section">
      <view class="e-header">
        <text class="tit">评价</text>
        <text>(86)</text>
        <text class="tip">好评率 100%</text>
        <text class="yticon icon-you"></text>
      </view>
      <view class="eva-box">
        <image class="portrait" src="http://img3.imgtn.bdimg.com/it/u=1150341365,1327279810&fm=26&gp=0.jpg"
               mode="aspectFill"></image>
        <view class="right">
          <text class="name">Leo yo</text>
          <text class="con">商品收到了，79元两件，质量不错，试了一下有点瘦，但是加个外罩很漂亮，我很喜欢</text>
          <view class="bot">
            <text class="attr">购买类型：XL 红色</text>
            <text class="time">2019-04-01 19:21</text>
          </view>
        </view>
      </view>
    </view>

    <view class="detail-desc">
      <view class="d-header"><text>图文详情</text></view>
      <rich-text :nodes="goodsInfo.detail"></rich-text>
    </view>

    <!-- 底部操作菜单 -->
    <view class="page-bottom">
      <navigator url="/pages/index/index" open-type="switchTab" class="p-b-btn">
        <text class="yticon icon-xiatubiao--copy"></text>
        <text>首页</text>
      </navigator>
      <navigator url="/pages/cart/cart" open-type="switchTab" class="p-b-btn">
        <text class="yticon icon-gouwuche"></text>
        <text>购物车</text>
      </navigator>
      <view class="p-b-btn" :class="{ active: favorite }" @click="toFavorite">
        <text class="yticon icon-shoucang"></text>
        <text>收藏</text>
      </view>

      <view class="action-btn-group">
        <button type="primary" class=" action-btn no-border buy-now-btn" @click="buy">立即购买</button>
        <button type="primary" class=" action-btn no-border add-cart-btn" @click="addToCart">加入购物车</button>
      </view>
    </view>
    <view class="popup spec" :class="couponClass" @touchmove.stop.prevent="stopPrevent" @click="toggleCoupon">
		<view class="mask"></view>
		<view class="layer coupon-content"  @click.stop="stopPrevent">
			<view class="coupon-title">优惠</view>
			<scroll-view style="padding-top: 10upx;" scroll-y = "true" class="coupon-box" >
				<view style="display: flex; margin: 20upx 0 " class="coupon_voucher3_main" v-for="item in 10" :key="item">
					<view class="coupon-box-left">
						<view class="coupon-box-left_price"><i >¥</i>5</view>
						<view class="coupon-box-left_des">满49元可用</view>
					</view>
					<view class="coupon-box-right">
						<view class="coupon-box-right_view"><i class="coupon-box-right_type"> <text style="background-color: #53c7ca;position: relative;padding: 2upx 6upx;">优惠券<text class="triangle"></text></text> </i>仅可购买男装部分商品
						</view>
						<text class="coupon-box-right_btn " v-if="false">领取</text>
						<text class="coupon-box-right_btn "  v-else style="background-color: #ccc;">已领取</text>
						<view class="coupon-box-right_date ">自领取后3天内有效</view>
					</view>
				</view>
			</scroll-view>

		</view>
    </view>
    <!-- 规格-模态层弹窗 -->
    <view class="popup  spec" :class="specClass" @touchmove.stop.prevent="stopPrevent" @click="toggleSpec">
      <!-- 遮罩层 -->
      <view class="mask"></view>
      <view class="layer attr-content" @click.stop="stopPrevent">
        <view class="a-t">
          <image :src="selectedSku.picUrl" />
          <view class="right">
            <text class="price">¥{{selectedSku.price/100}}</text>
            <text class="stock">
              <text v-if="!isNull">
                库存：{{selectedSku.stockNum}}件
              </text>
              <text v-else>
                无库存！
              </text>
            </text>
            <view class="selected">
              已选：
              <text class="selected-text" v-for="(sItem, sIndex) in selectedSpecValues"
                    :key="sIndex">{{ sItem.value }}</text>
            </view>
          </view>
        </view>
        <!-- 规格选项构建 -->
        <view v-for="(spec,specIndex) in specList" class="attr-list">
          <text>{{ spec.name }}</text>
          <view class="item-list">
            <text v-for="(specItem, specItemIndex) in spec.values" :key="specItem.id" class="tit"
                  :class="{ selected: specItem.selected }" @click="selectSpec(specIndex, specItemIndex)">
              {{ specItem.value }}
            </text>
          </view>
        </view>
        <button class="btn" @click="toggleSpec" :disabled="isNull">完成</button>
      </view>
    </view>
    <!-- 分享 -->
    <share ref="share" :contentHeight="580" :shareList="shareList"></share>
  </view>
</template>

<script>
import share from '@/components/share';

import {getSpuDetail, getStockNum} from '../../api/product/goods.js';

import {addCartItem} from "../../api/order/cart";

export default {
  components: {share},
  data() {
    return {
      goodsInfo: { // 商品信息
        id: undefined,
        name: undefined,
        originPrice: undefined,
        price: undefined,
        album: [],
        detail: undefined
      },
      attributeList: [], // 属性列表
      specList: [], // 规格列表
      skuList: [], // SKU列表
      specClass: 'none',
	  couponClass:'none',
      selectedSku: {},
      selectedSpecValues: [], // 选择的规格项集合
      favorite: true,
      shareList: [],
      isNull:false,
	  couponShow:false,
	  couponList:[]
    };
  },
  async onLoad(options) {
    console.log('========>> 进入商品详情页面, 路径:', this.$mp.page.route, '参数', options);
    const goodsId = options.id
    getSpuDetail(goodsId).then(response => {
      const {goodsInfo, attributeList, specList, skuList} = response.data;

      this.goodsInfo = goodsInfo;
      this.attributeList = attributeList;
      this.specList = specList;
      this.skuList = skuList;

      console.log('res:',response)

      //默认选择第一个sku
      this.selectedSku = this.skuList[0]
      console.log('selectedSku:',this.selectedSku)

      //默认选中的规格id
      const defaultSelectedSpecIds = this.selectedSku.specIds.split('_')

      this.specList.forEach(spec => {
        spec.values.forEach(vals=>{
          if(defaultSelectedSpecIds.indexOf(vals.id)!==-1){
            this.selectedSpecValues.push(vals)
            this.$set(vals, 'selected', true)
          }else{
            this.$set(vals, 'selected', false)
          }
        })
      })

      console.log('selectedSpecValues:',this.selectedSpecValues)

    });
  },
  methods: {
    //规格弹窗开关
    toggleSpec() {
      if (this.specClass === 'show') {
        this.specClass = 'hide';
        setTimeout(() => {
          this.specClass = 'none';
        }, 250);
      } else if (this.specClass === 'none') {
        this.specClass = 'show';
      }
    },
    // 选择规格
    selectSpec(specIndex, specValueIndex) {
      console.log('specIndex',specIndex)
      console.log('specValueIndex',specValueIndex)
      this.specList[specIndex].values.forEach((item, index) => {
        if (index == specValueIndex) {
          this.$set(item, 'selected', true);
        } else {
          this.$set(item, 'selected', false);
        }
      })

      this.selectedSpecValues = []
      this.specList.forEach(item => {
        const selectedSpecValue = item.values.filter(value => value.selected == true)[0]
        this.selectedSpecValues.push(selectedSpecValue)
      })

      const selectedSpecValueIds = this.selectedSpecValues.map(item => item.id)

      // 根据选择的规格项匹配商品库存单元
      let sku = this.skuList.filter(item => item.specIds.split('_').equals(selectedSpecValueIds))
      if(!sku || sku.length===0){
        this.selectedSku = {stockNum:0}
        this.isNull = true
      }else{
        this.selectedSku = sku[0]
        this.isNull = false
      }

      console.log('您选择的商品:', JSON.stringify(this.selectedSku))

    },
	//打开优惠券弹窗
	toggleCoupon() {
	  if (this.couponClass === 'show') {
	    this.couponClass = 'hide';
	    setTimeout(() => {
	      this.couponClass = 'none';
	    }, 250);
	  } else if (this.couponClass === 'none') {
	    this.couponClass = 'show';
	  }
	  // this.couponList = 
	},
    // 分享
    share() {
      this.$refs.share.toggleMask();
    },
    // 收藏
    toFavorite() {
      this.favorite = !this.favorite;
    },
    // 立即购买
    buy() {
      const skuId = this.selectedSku.id;
      uni.navigateTo({
        url: `/pages/order/createOrder?skuId=` + skuId + `&count=1`,
      });
    },
    // 添加至购物车
    addToCart() {
      const skuId = this.selectedSku.id

      addCartItem(skuId).then(response => {
        uni.switchTab({
          url: `/pages/cart/cart`,
          success: (res) => {
            let page = getCurrentPages().pop();
            if (page == undefined || page == null) return;
            page.curSegment = 0;
            page.loadData();
          },
        });
      })
    },
    stopPrevent() {}
  }
};

/**
 * 重写数组equals方法，数组元素完全相同不论顺序
 * @param target
 * @returns {boolean}
 */
Array.prototype.equals = function(target) {
  return this.length === target.length &&
      this.every(a => target.some(b => a === b)) &&
      target.every(x => this.some(y => x === y));
}
</script>

<style lang="scss">
page {
  background: $page-color-base;
  padding-bottom: 160upx;
}

.icon-you {
  font-size: $font-base + 2upx;
  color: #888;
}

.carousel {
  height: 722upx;
  position: relative;

  swiper {
    height: 100%;
  }

  .image-wrapper {
    width: 100%;
    height: 100%;
  }

  .swiper-item {
    display: flex;
    justify-content: center;
    align-content: center;
    height: 750upx;
    overflow: hidden;

    image {
      width: 100%;
      height: 100%;
    }
  }
}

/* 标题简介 */
.introduce-section {
  background: #fff;
  padding: 20upx 30upx;

  .title {
    font-size: 32upx;
    color: $font-color-dark;
    height: 50upx;
    line-height: 50upx;
  }

  .price-box {
    display: flex;
    align-items: baseline;
    height: 64upx;
    padding: 10upx 0;
    font-size: 26upx;
    color: $uni-color-primary;
  }

  .price {
    font-size: $font-lg + 2upx;
  }

  .m-price {
    margin: 0 12upx;
    color: $font-color-light;
    text-decoration: line-through;
  }

  .coupon-tip {
    align-items: center;
    padding: 4upx 10upx;
    background: $uni-color-primary;
    font-size: $font-sm;
    color: #fff;
    border-radius: 6upx;
    line-height: 1;
    transform: translateY(-4upx);
  }

  .bot-row {
    display: flex;
    align-items: center;
    height: 50upx;
    font-size: $font-sm;
    color: $font-color-light;

    text {
      flex: 1;
    }
  }
}

/* 分享 */
.share-section {
  display: flex;
  align-items: center;
  color: $font-color-base;
  background: linear-gradient(left, #fdf5f6, #fbebf6);
  padding: 12upx 30upx;

  .share-icon {
    display: flex;
    align-items: center;
    width: 70upx;
    height: 30upx;
    line-height: 1;
    border: 1px solid $uni-color-primary;
    border-radius: 4upx;
    position: relative;
    overflow: hidden;
    font-size: 22upx;
    color: $uni-color-primary;

    &:after {
      content: '';
      width: 50upx;
      height: 50upx;
      border-radius: 50%;
      left: -20upx;
      top: -12upx;
      position: absolute;
      background: $uni-color-primary;
    }
  }

  .icon-xingxing {
    position: relative;
    z-index: 1;
    font-size: 24upx;
    margin-left: 2upx;
    margin-right: 10upx;
    color: #fff;
    line-height: 1;
  }

  .tit {
    font-size: $font-base;
    margin-left: 10upx;
  }

  .icon-bangzhu1 {
    padding: 10upx;
    font-size: 30upx;
    line-height: 1;
  }

  .share-btn {
    flex: 1;
    text-align: right;
    font-size: $font-sm;
    color: $uni-color-primary;
  }

  .icon-you {
    font-size: $font-sm;
    margin-left: 4upx;
    color: $uni-color-primary;
  }
}

.c-list {
  font-size: $font-sm + 2upx;
  color: $font-color-base;
  background: #fff;

  .c-row {
    display: flex;
    align-items: center;
    padding: 20upx 30upx;
    position: relative;
  }

  .tit {
    width: 140upx;
  }

  .con {
    flex: 1;
    color: $font-color-dark;

    .selected-text {
      margin-right: 10upx;
    }
  }

  .bz-list {
    height: 40upx;
    font-size: $font-sm + 2upx;
    color: $font-color-dark;

    text {
      display: inline-block;
      margin-right: 30upx;
    }
  }

  .con-list {
    flex: 1;
    display: flex;
    flex-direction: column;
    color: $font-color-dark;
    line-height: 40upx;
  }

  .red {
    color: $uni-color-primary;
  }
}

/* 评价 */
.eva-section {
  display: flex;
  flex-direction: column;
  padding: 20upx 30upx;
  background: #fff;
  margin-top: 16upx;

  .e-header {
    display: flex;
    align-items: center;
    height: 70upx;
    font-size: $font-sm + 2upx;
    color: $font-color-light;

    .tit {
      font-size: $font-base + 2upx;
      color: $font-color-dark;
      margin-right: 4upx;
    }

    .tip {
      flex: 1;
      text-align: right;
    }

    .icon-you {
      margin-left: 10upx;
    }
  }
}

.eva-box {
  display: flex;
  padding: 20upx 0;

  .portrait {
    flex-shrink: 0;
    width: 80upx;
    height: 80upx;
    border-radius: 100px;
  }

  .right {
    flex: 1;
    display: flex;
    flex-direction: column;
    font-size: $font-base;
    color: $font-color-base;
    padding-left: 26upx;

    .con {
      font-size: $font-base;
      color: $font-color-dark;
      padding: 20upx 0;
    }

    .bot {
      display: flex;
      justify-content: space-between;
      font-size: $font-sm;
      color: $font-color-light;
    }
  }
}

/*  详情 */
.detail-desc {
  background: #fff;
  margin-top: 16upx;

  .d-header {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 80upx;
    font-size: $font-base + 2upx;
    color: $font-color-dark;
    position: relative;

    text {
      padding: 0 20upx;
      background: #fff;
      position: relative;
      z-index: 1;
    }

    &:after {
      position: absolute;
      left: 50%;
      top: 50%;
      transform: translateX(-50%);
      width: 300upx;
      height: 0;
      content: '';
      border-bottom: 1px solid #ccc;
    }
  }
}

/* 优惠券选择弹窗 */
.coupon-content{
	padding: 10upx 30upx;
	.coupon-title{
		font-size: 40upx;
		font-weight: 700;
		margin-bottom: 30upx;
		text-align: center;
	}
	.coupon-box-title{
		color: #262626;
		font-weight: 700;
		height: 40upx;
		// line-height: 40upx;
	}
	.coupon-box{
		max-height: 60vh;
		overflow-y: scroll;
		.coupon_voucher3_main{
			border-radius: 6upx;
			border-top: 6upx solid;
			box-shadow: 0 0 6upx 0 rgba(0, 0, 0, 0.1);
			box-shadow: #fff;
			color: #53c7ca;
			margin-bottom: 18upx;
			padding: 20upx 10upx;
			text-decoration: none;
			.coupon-box-left{
				display: flex;
				flex-direction: column;
				justify-content: center;
				line-height: 1;
				margin-right: 10upx;
				overflow: hidden;
				text-align: center;
				width: 200upx;
				.coupon-box-left_price{
					font-size: 60upx;
					font-weight: 900;
					i{
						font-weight: 400;
						font-size: 26upx;
						margin-right: 20upx;
						text-shadow: none;
						font-style: normal;
					}
				}
				.coupon-box-left_des{
					margin-top: 20upx;
					font-size: 30upx;
				}
			}
				.coupon-box-right{
					flex: 1;
					position: relative;
					-webkit-line-clamp: 2;
					-webkit-box-orient: vertical;
					color: #666;
					display: -webkit-box;
					font-size: 12upx;
					height: 36upx;
					.coupon-box-right_view{
						overflow: hidden;
						margin-bottom: 50upx;
						.coupon-box-right_type{
							font-style: normal;
							color: #fff;
							font-size: 12upx;
							display: inline-block;
							height: 35upx;
							padding-left: 25upx;
							position: relative;
							margin-right: 20upx;
							.triangle{
								border-bottom: 19upx solid transparent;
								border-top: 19upx solid transparent;
								border-right: 19upx solid  #53c7ca;
								// border-left: 20upx solid transparent;
								border-left: 0upx;
								position: absolute;
								left: -20upx;
								top: -1upx;
							}
						}
					}
					.coupon-box-right_btn{
						background-color: #53c7ca;
						// border: 1px solid #53c7ca;
						border-radius: 36upx;
						color: #fff;
						position: absolute;
						right: 0;
						width: 130upx;
						text-align: center;
						display: inline-block;
					}
				}
		}
	}
	
}

/* 规格选择弹窗 */
.attr-content {
  padding: 10upx 30upx;

  .a-t {
    display: flex;

    image {
      width: 170upx;
      height: 170upx;
      flex-shrink: 0;
      margin-top: -40upx;
      border-radius: 8upx;
    }

    .right {
      display: flex;
      flex-direction: column;
      padding-left: 24upx;
      font-size: $font-sm + 2upx;
      color: $font-color-base;
      line-height: 42upx;

      .price {
        font-size: $font-lg;
        color: $uni-color-primary;
        margin-bottom: 10upx;
      }

      .selected-text {
        margin-right: 10upx;
      }
    }
  }

  .attr-list {
    display: flex;
    flex-direction: column;
    font-size: $font-base + 2upx;
    color: $font-color-base;
    padding-top: 30upx;
    padding-left: 10upx;
  }

  .item-list {
    padding: 20upx 0 0;
    display: flex;
    flex-wrap: wrap;

    text {
      display: flex;
      align-items: center;
      justify-content: center;
      background: #eee;
      margin-right: 20upx;
      margin-bottom: 20upx;
      border-radius: 100upx;
      min-width: 60upx;
      height: 60upx;
      padding: 0 20upx;
      font-size: $font-base;
      color: $font-color-dark;
    }

    .selected {
      background: #fbebee;
      color: $uni-color-primary;
    }
  }
}

/*  弹出层 */
.popup {
  position: fixed;
  left: 0;
  top: 0;
  right: 0;
  bottom: 0;
  z-index: 99;

  &.show {
    display: block;

    .mask {
      animation: showPopup 0.2s linear both;
    }

    .layer {
      animation: showLayer 0.2s linear both;
    }
  }

  &.hide {
    .mask {
      animation: hidePopup 0.2s linear both;
    }

    .layer {
      animation: hideLayer 0.2s linear both;
    }
  }

  &.none {
    display: none;
  }

  .mask {
    position: fixed;
    top: 0;
    width: 100%;
    height: 100%;
    z-index: 1;
    background-color: rgba(0, 0, 0, 0.4);
  }

  .layer {
    position: fixed;
    z-index: 99;
    bottom: 0;
    width: 100%;
    min-height: 40vh;
    border-radius: 10upx 10upx 0 0;
    background-color: #fff;

    .btn {
      height: 66upx;
      line-height: 66upx;
      border-radius: 100upx;
      background: $uni-color-primary;
      font-size: $font-base + 2upx;
      color: #fff;
      margin: 30upx auto 20upx;
    }
  }

  @keyframes showPopup {
    0% {
      opacity: 0;
    }

    100% {
      opacity: 1;
    }
  }

  @keyframes hidePopup {
    0% {
      opacity: 1;
    }

    100% {
      opacity: 0;
    }
  }

  @keyframes showLayer {
    0% {
      transform: translateY(120%);
    }

    100% {
      transform: translateY(0%);
    }
  }

  @keyframes hideLayer {
    0% {
      transform: translateY(0);
    }

    100% {
      transform: translateY(120%);
    }
  }
}

/* 底部操作菜单 */
.page-bottom {
  position: fixed;
  left: 30upx;
  bottom: 30upx;
  z-index: 95;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 690upx;
  height: 100upx;
  background: rgba(255, 255, 255, 0.9);
  box-shadow: 0 0 20upx 0 rgba(0, 0, 0, 0.5);
  border-radius: 16upx;

  .p-b-btn {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    font-size: $font-sm;
    color: $font-color-base;
    width: 96upx;
    height: 80upx;

    .yticon {
      font-size: 40upx;
      line-height: 48upx;
      color: $font-color-light;
    }

    &.active,
    &.active .yticon {
      color: $uni-color-primary;
    }

    .icon-fenxiang2 {
      font-size: 42upx;
      transform: translateY(-2upx);
    }

    .icon-shoucang {
      font-size: 46upx;
    }
  }

  .action-btn-group {
    display: flex;
    height: 76upx;
    border-radius: 100px;
    overflow: hidden;
    box-shadow: 0 20upx 40upx -16upx #fa436a;
    box-shadow: 1px 2px 5px rgba(219, 63, 96, 0.4);
    background: linear-gradient(to right, #ffac30, #fa436a, #f56c6c);
    margin-left: 20upx;
    position: relative;

    &:after {
      content: '';
      position: absolute;
      top: 50%;
      right: 50%;
      transform: translateY(-50%);
      height: 28upx;
      width: 0;
      border-right: 1px solid rgba(255, 255, 255, 0.5);
    }

    .action-btn {
      display: flex;
      align-items: center;
      justify-content: center;
      width: 180upx;
      height: 100%;
      font-size: $font-base;
      padding: 0;
      border-radius: 0;
      background: transparent;
    }
  }
}
</style>

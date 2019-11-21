package com.tt.mybatis.sqlnode.iface;

import com.tt.mybatis.sqlnode.DynamicContext;

/**
 * @author lizhuo
 * @Description: 封装不同的sql脚本，提供sql脚本处理功能
 * @date 2019-11-20 17:45
 */
public interface SqlNode {

	void apply(DynamicContext context);

}

package com.cacard.demo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class DiscardServerHandler extends ChannelHandlerAdapter {

	@Override 
	public void channelRead(ChannelHandlerContext ctx,Object msg)
	{
		((ByteBuf)msg).release();
	}
	
}

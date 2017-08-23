package org.genfork.test_project;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.genfork.test_project.network.ServerHandler;

import java.net.InetSocketAddress;

/**
 * User: <a href="http://gencloud.solutions">GenCloud</a>
 * Date: 2017/07
 */
public class ServerApp {

	private final int port;

	public ServerApp(int port) {
		this.port = port;
	}

	public static void main(String[] args) throws Exception {
//		if( args.length != 1 ) {
//			System.err.println("usage: java ServerApp port");
//			System.exit(1);
//		}
//
//		int port = Integer.parseInt(args[0]);
		new ServerApp(10000).start();
	}

	public void start() throws Exception {

		final ServerHandler serverHandler = new ServerHandler();

		final NioEventLoopGroup bossGroup = new NioEventLoopGroup();
		final NioEventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			final ServerBootstrap b = new ServerBootstrap()
					.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.localAddress(new InetSocketAddress(port))
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new ObjectEncoder(),
									new ObjectDecoder(ClassResolvers.weakCachingResolver(ClassLoader.getSystemClassLoader())),
									serverHandler);
						}
					}).childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

			// connect

			ChannelFuture f = b.bind();

			// close

			f.channel().closeFuture().sync();

		} finally {
			bossGroup.shutdownGracefully().sync();
			workerGroup.shutdownGracefully().sync();
		}

		Runtime.getRuntime().addShutdownHook(new ShutdownHook());
	}
}

/**
 * 
 */
package com.nathanclaire.alantra.channel.server;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;
import org.jboss.netty.handler.codec.serialization.ObjectEncoder;

/**
 * @author Edward Banfa 
 *
 */
public class TransactionAuthorizationPipelineBuilder implements PipelineBuilder{

	//private ClassResolver classResolver = ClassResolvers.softCachingConcurrentResolver(Bootstrap.class.getClassLoader());
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.server.PipelineBuilder#build(com.nathanclaire.alantra.channel.server.PipelineConfiguration)
	 */
	@Override
	public ChannelPipeline build(PipelineConfiguration configuration) {
		// Create a default pipeline implementation.
        ChannelPipeline pipeline = Channels.pipeline();;

        // Uncomment the following line if you want HTTPS
        //SSLEngine engine = SecureChatSslContextFactory.getServerContext().createSSLEngine();
        //engine.setUseClientMode(false);
        //pipeline.addLast("ssl", new SslHandler(engine));

        pipeline.addLast("decoder", new ObjectDecoder());
        // Uncomment the following line if you don't want to handle HttpChunks.
        //pipeline.addLast("aggregator", new HttpChunkAggregator(1048576));
        pipeline.addLast("encoder", new ObjectEncoder());
        pipeline.addLast("txn-authorizer", new TransactionAuthorizerHandler());
        return pipeline;
	}

}

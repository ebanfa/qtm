/**
 * 
 */
package com.nathanclaire.alantra.channel.server;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.http.HttpContentCompressor;
import org.jboss.netty.handler.codec.http.HttpRequestDecoder;
import org.jboss.netty.handler.codec.http.HttpResponseEncoder;

/**
 * @author Edward Banfa 
 *
 */
public class HTTPISO8583PipelineBuilder implements PipelineBuilder {

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

        pipeline.addLast("decoder", new HttpRequestDecoder());
        // Uncomment the following line if you don't want to handle HttpChunks.
        //pipeline.addLast("aggregator", new HttpChunkAggregator(1048576));
        pipeline.addLast("encoder", new HttpResponseEncoder());
        // Remove the following line if you don't want automatic content compression.
        pipeline.addLast("deflater", new HttpContentCompressor());
        pipeline.addLast("handler", new HttpSnoopServerHandler());
        return pipeline;
	}

}

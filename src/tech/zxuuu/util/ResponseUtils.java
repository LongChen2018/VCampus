package tech.zxuuu.util;

import tech.zxuuu.client.messageQueue.ResponseQueue;
import tech.zxuuu.net.Response;

public final class ResponseUtils {

	@Deprecated 
	/**
	 * @see `getResponseByHash` might be better
	 */
	public final static void blockAndWaitResponse(String hash) {
		while (!ResponseQueue.getInstance().contain(hash)) {
			if (ResponseQueue.getInstance().contain(hash)) {
				break;
			}
		}
	}
	
	public final static Response getResponseByHash(String hash) {
		while (!ResponseQueue.getInstance().contain(hash)) {
			if (ResponseQueue.getInstance().contain(hash)) {
				break;
			}
		}
		return ResponseQueue.getInstance().consume(hash);
	}
	
}

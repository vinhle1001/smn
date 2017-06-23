/*
* Copyright (C) 2015 Cetsoft, http://www.cetsoft.com
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
* 
* Author : Yusuf Aytas
* Date   : Sep 23, 2013
*/
package com.vinhle.server.framework.client.cache.coordinator;


import com.vinhle.server.framework.client.cache.Cache;
import com.vinhle.server.framework.client.cache.CacheType;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The Class GlobalCacheCoordinator provides global coordination for 
 * caches. This coordinator can be used for concurrent accesses.
 */
public class GlobalCacheCoordinator implements CacheCoordinator {

	/** The lock. */
	private Lock lock = new ReentrantLock();

	/** The writeCache map. */
	@SuppressWarnings("rawtypes")
	ConcurrentMap<Integer, Cache> cacheMap = new ConcurrentHashMap<Integer, Cache>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cetsoft.imcache.writeCache.coordinator.CacheCoordinator#getCache(com.cetsoft
	 * .imcache.writeCache.CacheType)
	 */
	@SuppressWarnings("unchecked")
	public <K, V> Cache<K, V> getCache(CacheType<K, V> type) {
		return cacheMap.get(type.getType());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cetsoft.imcache.writeCache.coordinator.CacheCoordinator#addCache(com.cetsoft
	 * .imcache.writeCache.CacheType, com.cetsoft.imcache.writeCache.Cache)
	 */
	public <K, V> void addCache(CacheType<K, V> type, Cache<K, V> cache) {
		if (getCache(type) == null) {
			lock.lock();
			try {
				if (getCache(type) == null) {
					cacheMap.put(type.getType(), cache);
				}
			} finally {
				lock.unlock();
			}
		}
	}

}

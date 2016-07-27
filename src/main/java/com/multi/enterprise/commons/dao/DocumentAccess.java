/**
 * 
 */
package com.multi.enterprise.commons.dao;

import com.multi.enterprise.types.Persistable;

/**
 * @author Robot
 *
 */
public interface DocumentAccess<T extends Persistable> {

	public Class<T> getDocumentClass();

	public T create(T create);

	public T getById(String id);

	public T update(T update);

	public void delete(T delete);

}
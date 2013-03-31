/**
 * 
 */
package com.nathanclaire.alantra.rest.response;

import java.util.ArrayList;
import java.util.List;

import com.nathanclaire.alantra.model.product.ProductType;

/**
 * @author Edward Banfa 
 *
 */
public class ProductOrderMobileResponse extends BaseResponse
{
	List<ProductType> producTypes = new ArrayList<ProductType>();
}

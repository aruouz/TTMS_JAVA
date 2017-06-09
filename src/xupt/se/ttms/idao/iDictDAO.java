/**
 * 
 */
package xupt.se.ttms.idao;
import xupt.se.ttms.model.Dict;
import xupt.se.ttms.model.Studio;

import java.util.List;

/**
 * @author Administrator
 *
 */
public interface iDictDAO {
	public int insert(Dict dic);
	public int update(Dict dic);
	public int delete(int ID);
	public List<Dict> select(String condt); 
}

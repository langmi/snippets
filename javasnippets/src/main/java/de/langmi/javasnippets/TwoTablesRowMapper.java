/**
 * Copyright 2011 Michael R. Lange <michael.r.lange@langmi.de>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.langmi.javasnippets;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.jdbc.core.RowMapper;

/**
 * TwoTablesRowMapper.
 *
 * @author Michael R. Lange <michael.r.lange@langmi.de>
 */
public class TwoTablesRowMapper implements RowMapper<Map<String, Object>> {

    /**
     * Map data from select over 2 tables e.g.:
     * 
     * select 
     *    A.foo as afoo, 
     *    B.bar as bbar
     * from PARENT A, 
     *      CHILD B
     * where A.ID = B.ID
     * 
     * 
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException 
     */
    @Override
    public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        // instead of a map one could fill an object
        // e.g.: myObject.set.afoo(afoo)
        resultMap.put("afoo", rs.getString("afoo"));
        resultMap.put("bbar", rs.getString("bbar"));
        
        return resultMap;
    }
}

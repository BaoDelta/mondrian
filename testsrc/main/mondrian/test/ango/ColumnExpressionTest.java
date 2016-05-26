package mondrian.test.ango;

import mondrian.test.FoodMartTestCase;

import org.apache.commons.lang.StringUtils;

public class ColumnExpressionTest extends FoodMartTestCase {

    public void testTemplate() {
        {
            String query = StringUtils.join(new String[] {}, "\n");
            String result = StringUtils.join(new String[] {}, "\n");
            // assertQueryReturns(query, result);
            assertEquals(query, "");
            assertEquals(result, "");
        }
    }

    public void testNormalColumns() {
        {
            String query = StringUtils.join(new String[] { "SELECT", //
                    "  [Store].[USA].children ON ROWS,", //
                    "  [Measures].[Unit Sales] ON COLUMNS", //
                    "FROM [Sales]" //
            }, "\n");
            String result = StringUtils.join(new String[] { "Axis #0:", //
                    "{}", //
                    "Axis #1:", //
                    "{[Measures].[Unit Sales]}", //
                    "Axis #2:", //
                    "{[Store].[USA].[CA]}", //
                    "{[Store].[USA].[OR]}", //
                    "{[Store].[USA].[WA]}", //
                    "Row #0: 74,748", //
                    "Row #1: 67,659", //
                    "Row #2: 124,366", //
                    "" //
            }, "\n");
            assertQueryReturns(query, result);
        }
    }

    public void testLevelExp() {
        {
            String query = StringUtils.join(new String[] { "SELECT", //
                    "  [Store.LevelExp].[USA].children ON ROWS,", //
                    "  [Measures].[Unit Sales] ON COLUMNS", //
                    "FROM [Sales]" //
            }, "\n");
            String result = StringUtils.join(new String[] { "Axis #0:", //
                    "{}", //
                    "Axis #1:", //
                    "{[Measures].[Unit Sales]}", //
                    "Axis #2:", //
                    "{[Store.LevelExp].[USA].[CA]}", //
                    "{[Store.LevelExp].[USA].[OR]}", //
                    "{[Store.LevelExp].[USA].[WA]}", //
                    "Row #0: 74,748", //
                    "Row #1: 67,659", //
                    "Row #2: 124,366", //
                    "" //
            }, "\n");
            assertQueryReturns(query, result);
        }
        {
            String query = StringUtils.join(new String[] { "SELECT", //
                    "  Descendants([Store.LevelExp], [Store.LevelExp].[Store City]) ON ROWS,", //
                    "  {} ON COLUMNS", //
                    "FROM [Sales]" //
            }, "\n");
            String result = StringUtils.join(new String[] { "Axis #0:", //
                    "{}", //
                    "Axis #1:", //
                    "Axis #2:", //
                    "{[Store.LevelExp].[Canada].[BC].[Vancouver]}", //
                    "{[Store.LevelExp].[Canada].[BC].[Victoria]}", //
                    "{[Store.LevelExp].[Mexico].[DF].[Mexico City]}", //
                    "{[Store.LevelExp].[Mexico].[DF].[San Andres]}", //
                    "{[Store.LevelExp].[Mexico].[Guerrero].[Acapulco]}", //
                    "{[Store.LevelExp].[Mexico].[Jalisco].[Guadalajara]}", //
                    "{[Store.LevelExp].[Mexico].[Veracruz].[Orizaba]}", //
                    "{[Store.LevelExp].[Mexico].[Yucatan].[Merida]}", //
                    "{[Store.LevelExp].[Mexico].[Zacatecas].[Camacho]}", //
                    "{[Store.LevelExp].[Mexico].[Zacatecas].[Hidalgo]}", //
                    "{[Store.LevelExp].[USA].[CA].[Alameda]}", //
                    "{[Store.LevelExp].[USA].[CA].[Beverly Hills]}", //
                    "{[Store.LevelExp].[USA].[CA].[Los Angeles]}", //
                    "{[Store.LevelExp].[USA].[CA].[San Diego]}", //
                    "{[Store.LevelExp].[USA].[CA].[San Francisco]}", //
                    "{[Store.LevelExp].[USA].[OR].[Portland]}", //
                    "{[Store.LevelExp].[USA].[OR].[Salem]}", //
                    "{[Store.LevelExp].[USA].[WA].[Bellingham]}", //
                    "{[Store.LevelExp].[USA].[WA].[Bremerton]}", //
                    "{[Store.LevelExp].[USA].[WA].[Seattle]}", //
                    "{[Store.LevelExp].[USA].[WA].[Spokane]}", //
                    "{[Store.LevelExp].[USA].[WA].[Tacoma]}", //
                    "{[Store.LevelExp].[USA].[WA].[Walla Walla]}", //
                    "{[Store.LevelExp].[USA].[WA].[Yakima]}", //
                    "" //
            }, "\n");
            assertQueryReturns(query, result);
        }
    }

}

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
                    "  [Store.Level Exp].[USA].children ON ROWS,", //
                    "  [Measures].[Unit Sales] ON COLUMNS", //
                    "FROM [Sales]" //
            }, "\n");
            String result = StringUtils.join(new String[] { "Axis #0:", //
                    "{}", //
                    "Axis #1:", //
                    "{[Measures].[Unit Sales]}", //
                    "Axis #2:", //
                    "{[Store.Level Exp].[USA].[CA]}", //
                    "{[Store.Level Exp].[USA].[OR]}", //
                    "{[Store.Level Exp].[USA].[WA]}", //
                    "Row #0: 74,748", //
                    "Row #1: 67,659", //
                    "Row #2: 124,366", //
                    "" //
            }, "\n");
            assertQueryReturns(query, result);
        }
        {
            String query = StringUtils.join(new String[] { "SELECT", //
                    "  Descendants([Store.Level Exp], [Store.Level Exp].[Store City]) ON ROWS,", //
                    "  {} ON COLUMNS", //
                    "FROM [Sales]" //
            }, "\n");
            String result = StringUtils.join(new String[] { "Axis #0:", //
                    "{}", //
                    "Axis #1:", //
                    "Axis #2:", //
                    "{[Store.Level Exp].[Canada].[BC].[Vancouver]}", //
                    "{[Store.Level Exp].[Canada].[BC].[Victoria]}", //
                    "{[Store.Level Exp].[Mexico].[DF].[Mexico City]}", //
                    "{[Store.Level Exp].[Mexico].[DF].[San Andres]}", //
                    "{[Store.Level Exp].[Mexico].[Guerrero].[Acapulco]}", //
                    "{[Store.Level Exp].[Mexico].[Jalisco].[Guadalajara]}", //
                    "{[Store.Level Exp].[Mexico].[Veracruz].[Orizaba]}", //
                    "{[Store.Level Exp].[Mexico].[Yucatan].[Merida]}", //
                    "{[Store.Level Exp].[Mexico].[Zacatecas].[Camacho]}", //
                    "{[Store.Level Exp].[Mexico].[Zacatecas].[Hidalgo]}", //
                    "{[Store.Level Exp].[USA].[CA].[Alameda]}", //
                    "{[Store.Level Exp].[USA].[CA].[Beverly Hills]}", //
                    "{[Store.Level Exp].[USA].[CA].[Los Angeles]}", //
                    "{[Store.Level Exp].[USA].[CA].[San Diego]}", //
                    "{[Store.Level Exp].[USA].[CA].[San Francisco]}", //
                    "{[Store.Level Exp].[USA].[OR].[Portland]}", //
                    "{[Store.Level Exp].[USA].[OR].[Salem]}", //
                    "{[Store.Level Exp].[USA].[WA].[Bellingham]}", //
                    "{[Store.Level Exp].[USA].[WA].[Bremerton]}", //
                    "{[Store.Level Exp].[USA].[WA].[Seattle]}", //
                    "{[Store.Level Exp].[USA].[WA].[Spokane]}", //
                    "{[Store.Level Exp].[USA].[WA].[Tacoma]}", //
                    "{[Store.Level Exp].[USA].[WA].[Walla Walla]}", //
                    "{[Store.Level Exp].[USA].[WA].[Yakima]}", //
                    "" //
            }, "\n");
            assertQueryReturns(query, result);
        }
    }

    public void testMeasureExp() {
        {
            String query = StringUtils.join(new String[] { "SELECT", //
                    "  [Store.Level Exp].[USA].children ON ROWS,", //
                    "  [Measures].[Unit Sales Exp] ON COLUMNS", //
                    "FROM [Sales]" //
            }, "\n");
            String result = StringUtils.join(new String[] { "Axis #0:", //
                    "{}", //
                    "Axis #1:", //
                    "{[Measures].[Unit Sales Exp]}", //
                    "Axis #2:", //
                    "{[Store.Level Exp].[USA].[CA]}", //
                    "{[Store.Level Exp].[USA].[OR]}", //
                    "{[Store.Level Exp].[USA].[WA]}", //
                    "Row #0: 74,748", //
                    "Row #1: 67,659", //
                    "Row #2: 124,366", //
                    "" //
            }, "\n");
            assertQueryReturns(query, result);
        }
    }

    public void testDimensionUsageExp() {
        {
            String query = StringUtils.join(new String[] { "SELECT", //
                    "  [Store Exp.Level Exp].[USA].children ON ROWS,", //
                    "  [Measures].[Unit Sales Exp] ON COLUMNS", //
                    "FROM [Sales]" //
            }, "\n");
            String result = StringUtils.join(new String[] { "Axis #0:", //
                    "{}", //
                    "Axis #1:", //
                    "{[Measures].[Unit Sales Exp]}", //
                    "Axis #2:", //
                    "{[Store Exp.Level Exp].[USA].[CA]}", //
                    "{[Store Exp.Level Exp].[USA].[OR]}", //
                    "{[Store Exp.Level Exp].[USA].[WA]}", //
                    "Row #0: 74,748", //
                    "Row #1: 67,659", //
                    "Row #2: 124,366", //
                    "" //
            }, "\n");
            assertQueryReturns(query, result);
        }
    }

    public void testHierarchyExp() {
        {
            String query = StringUtils.join(new String[] { "SELECT", //
                    "  [Store Exp.Hierarchy Exp].[USA].children ON ROWS,", //
                    "  [Measures].[Unit Sales Exp] ON COLUMNS", //
                    "FROM [Sales]" //
            }, "\n");
            String result = StringUtils.join(new String[] { "Axis #0:", //
                    "{}", //
                    "Axis #1:", //
                    "{[Measures].[Unit Sales Exp]}", //
                    "Axis #2:", //
                    "{[Store Exp.Hierarchy Exp].[USA].[CA]}", //
                    "{[Store Exp.Hierarchy Exp].[USA].[OR]}", //
                    "{[Store Exp.Hierarchy Exp].[USA].[WA]}", //
                    "Row #0: 74,748", //
                    "Row #1: 67,659", //
                    "Row #2: 124,366", //
                    "" //
            }, "\n");
            assertQueryReturns(query, result);
        }
    }

}

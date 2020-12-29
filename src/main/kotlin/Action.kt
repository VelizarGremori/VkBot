enum class Action(val actionName : String) {
    START("{\"command\":\"start\"}"),
    CONFIRM_NAME("{\"command\":\"confirmName\"}"),
    WAIT("{\"command\":\"wait\"}"),
    START_SELECT("{\"command\":\"startSelectHomework\"}"),
    SELECT_HW("{\"command\":\"selectHomework\"}"),
    PUSH_HW("{\"command\":\"pushHomework\"}")

}
// Generated JS from Java: Game.Slots.Positions -----
function Game_Slots_Positions(type, inPlay) {
    this.fromHand = null;
    this.onTable = null;
 
    jv_Object.call(this);
    this._Game_Slots_PositionsInit();
    this.fromHand = inPlay;
    this.onTable = type;
}
 
var Game_Slots_Positions_c = sc_newClass("Game.Slots.Positions", Game_Slots_Positions, jv_Object, null);

Game_Slots_Positions_c.toString = function ()  {
    if (this.hasOwnProperty("$protoName")) {
        return jv_Class_c.toString.apply(this, arguments);
    }
    return this.fromHand.toString();
};
Game_Slots_Positions_c.currentPlace = function ()  {
    return this.onTable;
};
Game_Slots_Positions_c.getStats = function ()  {
    return this.fromHand.getValue(this.onTable);
};
Game_Slots_Positions_c.getAtk = function ()  {
    return this.fromHand.getValue(this.onTable)[0];
};
Game_Slots_Positions_c.getDef = function ()  {
    return this.fromHand.getValue(this.onTable)[1];
};
Game_Slots_Positions_c.getSlot = function ()  {
    return this.fromHand;
};
Game_Slots_Positions_c.remove = function ()  {
    this.fromHand = null;
};
Game_Slots_Positions_c.isEmpty = function ()  {
    return this.fromHand === null;
};

Game_Slots_Positions_c._Game_Slots_PositionsInit = function() {
};

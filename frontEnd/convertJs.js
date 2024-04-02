// Generated JS from Java: Game.Player -----
function Game_Player() {
}

var Game_Player_c = sc_newClass("Game.Player", Game_Player, null, null);



// Generated JS from Java: Game.Card -----
function Game_Card(ID, name, uAtk, atk, cDef, corn, def, prime, secondary) {
   this.cardName = null;
   this.id = 0;
   this.item = false;
   this.cardTypeONE = null;
   this.cardTypeTWO = null;
   this.positionMap = null;

   jv_Object.call(this);
   this._Game_CardInit();
   this.cardName = name;
   this.id = ID;
   this.positionMap.put(UBER, uAtk);
   this.positionMap.put(ATTACK, atk);
   this.positionMap.put(CoreDEFENCE, cDef);
   this.positionMap.put(CORE, corn);
   this.positionMap.put(DEFENCE, def);
   this.cardTypeONE = prime;
   this.cardTypeTWO = secondary;
}

var Game_Card_c = sc_newClass("Game.Card", Game_Card, jv_Object, null);

Game_Card_c.toString = function ()  {
   if (this.hasOwnProperty("$protoName")) {
      return jv_Class_c.toString.apply(this, arguments);
   }
   return this.id + " " + this.cardName + " " + this.cardTypeONE + " " + this.cardTypeTWO + " ";
};
Game_Card_c.getValue = function (boardPos)  {
   if (arguments.length == 0) return;
   return this.positionMap.get(boardPos);
};
Game_Card_c.activeTypeOne = function ()  {
   return this.cardTypeONE;
};
Game_Card_c.activeTypeTwo = function ()  {
   return this.cardTypeTWO;
};
Game_Card_c.useAsItem = function ()  {
   this.item = true;
};
Game_Card_c.isItem = function ()  {
   return this.item;
};
Game_Card_c.getId = function ()  {
   return this.id;
};

Game_Card_c._Game_CardInit = function() {
   this.item = false;
      ;
   this.positionMap = new jv_HashMap();
      ;
};

// Generated JS from Java: Game.Player -----
function Game_Player() {
}

var Game_Player_c = sc_newClass("Game.Player", Game_Player, null, null);



// Generated JS from Java: Game.Human -----
function Game_Human() {
   this.r = null;
   this.cards = null;
   this.deck = null;
   this.hand = null;
   this.b = null;

   jv_Object.call(this);
   this._Game_HumanInit();
   new jv_Scanner(jv_System_c._in);
}

var Game_Human_c = sc_newClass("Game.Human", Game_Human, jv_Object, [Game_Player]);

Game_Human_c.makeDeck = function ()  {
   this.cards.cardData();
   var deckSize = 30;
   for (var i = 0; i < deckSize; i++) {
      var shuffle = this.r.nextInt(1, this.cards.pack.size() - 1);
      this.deck.add(this.cards.pack.get(shuffle));
   }
};
Game_Human_c.getDeck = function ()  {
   return this.deck;
};
Game_Human_c.makeHand = function ()  {
   this.cards.cardData();
   var handSize = 5;
   for (var i = 0; i < handSize; i++) {
      var shuffle = this.r.nextInt(0, this.deck.size() - 1);
      this.hand.add(this.deck.get(shuffle));
   }
};
Game_Human_c.getHand = function ()  {
   return this.hand;
};
Game_Human_c.hasItem = function ()  {
   for (var _i = this.hand.iterator(); _i.hasNext();) {
      var i = _i.next();
      if (i.activeTypeOne().equals("ITEM")) {
         return true;
      }
   }
   return false;
};
Game_Human_c.getBoard = function ()  {
   return this.b;
};
Game_Human_c.getGrid = function ()  {
   return this.b.getGrid();
};
Game_Human_c.placeCard = function (pos, card)  {
   this.b.addToSlots(pos, this.hand.get(card));
   this.hand.remove(card);
};
Game_Human_c.pickUpCard = function ()  {
   var up;
   var shuffle = this.r.nextInt(0, this.deck.size());
   up = this.deck.get(shuffle);
   if (this.deck.size() === 0) {
      jv_System_c.out.println("No more cards, SUFFER");
      return;
   }
   this.hand.add(up);
   this.deck.remove(shuffle);
};
Game_Human_c.discardHandCard = function (c)  {
   this.deck.add(this.hand.get(c));
   this.hand.remove(c);
};
Game_Human_c.removePlacedCard = function (pos, slotNum)  {
   this.b.removeFromSlots(pos, slotNum);
};
Game_Human_c.selectOtherPlayerCard = function (other, row, col)  {
   var selectedCard = other.getGrid()[row][col].getSlot();
   if (other.getGrid()[row][col].isEmpty() && selectedCard.isItem()) {
      jv_System_c.exit(1);
   }
   return selectedCard;
};
Game_Human_c.checkCard = function (row, col)  {
   var thisCard = this.getGrid()[row][col].getSlot();
   if (thisCard === null) {
      jv_System_c.out.println("This is null");
      jv_System_c.exit(1);
   }
   if (this.getGrid()[row][col].isEmpty() && thisCard.isItem()) {
      jv_System_c.exit(1);
   }
   return thisCard;
};

Game_Human_c._Game_HumanInit = function() {
   this.r = new jv_Random();
      ;
   this.cards = new Game_cardDatabase();
      ;
   this.deck = new jv_ArrayList();
      ;
   this.hand = new jv_ArrayList();
      ;
   this.b = new Board();
      ;
};

// Generated JS from Java: Game.Card -----
function Game_Card(ID, name, uAtk, atk, cDef, corn, def, prime, secondary) {
   this.cardName = null;
   this.id = 0;
   this.item = false;
   this.cardTypeONE = null;
   this.cardTypeTWO = null;
   this.positionMap = null;

   jv_Object.call(this);
   this._Game_CardInit();
   this.cardName = name;
   this.id = ID;
   this.positionMap.put(UBER, uAtk);
   this.positionMap.put(ATTACK, atk);
   this.positionMap.put(CoreDEFENCE, cDef);
   this.positionMap.put(CORE, corn);
   this.positionMap.put(DEFENCE, def);
   this.cardTypeONE = prime;
   this.cardTypeTWO = secondary;
}

var Game_Card_c = sc_newClass("Game.Card", Game_Card, jv_Object, null);

Game_Card_c.toString = function ()  {
   if (this.hasOwnProperty("$protoName")) {
      return jv_Class_c.toString.apply(this, arguments);
   }
   return this.id + " " + this.cardName + " " + this.cardTypeONE + " " + this.cardTypeTWO + " ";
};
Game_Card_c.getValue = function (boardPos)  {
   if (arguments.length == 0) return;
   return this.positionMap.get(boardPos);
};
Game_Card_c.activeTypeOne = function ()  {
   return this.cardTypeONE;
};
Game_Card_c.activeTypeTwo = function ()  {
   return this.cardTypeTWO;
};
Game_Card_c.useAsItem = function ()  {
   this.item = true;
};
Game_Card_c.isItem = function ()  {
   return this.item;
};
Game_Card_c.getId = function ()  {
   return this.id;
};

Game_Card_c._Game_CardInit = function() {
   this.item = false;
      ;
   this.positionMap = new jv_HashMap();
      ;
};

// Generated JS from Java: Game.cardDatabase -----
function Game_cardDatabase() {
   this.pack = null;
   this.formPack = null;
   jv_Object.call(this);
   this._Game_cardDatabaseInit();
}

var Game_cardDatabase_c = sc_newClass("Game.cardDatabase", Game_cardDatabase, jv_Object, null);

Game_cardDatabase_c.cardData = function ()  {
   this.pack.add(new Game_Card(-1, "Blank", sc_initArray(Number_c, 1, [ 0, 0 ]),
              sc_initArray(Number_c, 1, [ 0, 0 ]), sc_initArray(Number_c, 1, [ 0, 0 ]),
              sc_initArray(Number_c, 1, [ 0, 0 ]), sc_initArray(Number_c, 1, [ 0, 0 ]),
              "NEUTRAL", "NEUTRAL"));
   this.pack.add(new Game_Card(0, "Basic_Seeds", sc_initArray(Number_c, 1, [ 4, 2 ]),
              sc_initArray(Number_c, 1, [ 2, 2 ]), sc_initArray(Number_c, 1, [ 2, 3 ]),
              sc_initArray(Number_c, 1, [ 2, 4 ]), sc_initArray(Number_c, 1, [ 1, 4 ]),
              "PLANT", "FARM"));
   this.pack.add(new Game_Card(1, "Simple_Axe", sc_initArray(Number_c, 1, [ 4, 3 ]),
              sc_initArray(Number_c, 1, [ 3, 3 ]), sc_initArray(Number_c, 1, [ 3, 4 ]),
              sc_initArray(Number_c, 1, [ 3, 5 ]), sc_initArray(Number_c, 1, [ 2, 4 ]),
              "TOOL", "NEUTRAL"));
   this.pack.add(new Game_Card(2, "Feather", sc_initArray(Number_c, 1, [ 3, 1 ]),
              sc_initArray(Number_c, 1, [ 3, 1 ]), sc_initArray(Number_c, 1, [ 4, 2 ]),
              sc_initArray(Number_c, 1, [ 3, 3 ]), sc_initArray(Number_c, 1, [ 5, 3 ]),
              "ANIMAL", "FARM"));
   this.pack.add(new Game_Card(3, "Flaccid_Flower", sc_initArray(Number_c, 1, [ 2, 1 ]),
              sc_initArray(Number_c, 1, [ 1, 1 ]), sc_initArray(Number_c, 1, [ 1, 3 ]),
              sc_initArray(Number_c, 1, [ 1, 3 ]), sc_initArray(Number_c, 1, [ 1, 2 ]),
              "FLOWER", "PLANT"));
   this.pack.add(new Game_Card(4, "Gourd_Seeds", sc_initArray(Number_c, 1, [ 1, 4 ]),
              sc_initArray(Number_c, 1, [ 1, 4 ]), sc_initArray(Number_c, 1, [ 2, 4 ]),
              sc_initArray(Number_c, 1, [ 2, 4 ]), sc_initArray(Number_c, 1, [ 3, 4 ]),
              "PLANT", "FARM"));
   this.pack.add(new Game_Card(5, "Pumpkin", sc_initArray(Number_c, 1, [ 2, 5 ]),
              sc_initArray(Number_c, 1, [ 2, 5 ]), sc_initArray(Number_c, 1, [ 3, 5 ]),
              sc_initArray(Number_c, 1, [ 3, 5 ]), sc_initArray(Number_c, 1, [ 3, 5 ]),
              "PLANT", "FARM"));
   this.pack.add(new Game_Card(6, "Apple", sc_initArray(Number_c, 1, [ 3, 2 ]),
              sc_initArray(Number_c, 1, [ 3, 2 ]), sc_initArray(Number_c, 1, [ 2, 3 ]),
              sc_initArray(Number_c, 1, [ 2, 3 ]), sc_initArray(Number_c, 1, [ 2, 3 ]),
              "PLANT", "FARM"));
   this.pack.add(new Game_Card(7, "Mutton", sc_initArray(Number_c, 1, [ 4, 1 ]),
              sc_initArray(Number_c, 1, [ 3, 2 ]), sc_initArray(Number_c, 1, [ 3, 3 ]),
              sc_initArray(Number_c, 1, [ 3, 3 ]), sc_initArray(Number_c, 1, [ 2, 3 ]),
              "ANIMAL", "FARM"));
   this.pack.add(new Game_Card(8, "HayBale", sc_initArray(Number_c, 1, [ 3, 3 ]),
              sc_initArray(Number_c, 1, [ 3, 3 ]), sc_initArray(Number_c, 1, [ 2, 4 ]),
              sc_initArray(Number_c, 1, [ 2, 4 ]), sc_initArray(Number_c, 1, [ 2, 5 ]),
              "PLANT", "FARM"));
   this.pack.add(new Game_Card(9, "Shears", sc_initArray(Number_c, 1, [ 3, 1 ]),
              sc_initArray(Number_c, 1, [ 2, 1 ]), sc_initArray(Number_c, 1, [ 1, 2 ]),
              sc_initArray(Number_c, 1, [ 1, 2 ]), sc_initArray(Number_c, 1, [ 1, 2 ]),
              "ANIMAL", "FARM"));
   this.pack.add(new Game_Card(10, "Cornflower", sc_initArray(Number_c, 1, [ 2, 1 ]),
              sc_initArray(Number_c, 1, [ 2, 1 ]), sc_initArray(Number_c, 1, [ 1, 1 ]),
              sc_initArray(Number_c, 1, [ 1, 1 ]), sc_initArray(Number_c, 1, [ 1, 1 ]),
              "FLOWER", "FARM"));
   this.pack.add(new Game_Card(11, "Sapling", sc_initArray(Number_c, 1, [ 3, 1 ]),
              sc_initArray(Number_c, 1, [ 2, 1 ]), sc_initArray(Number_c, 1, [ 1, 2 ]),
              sc_initArray(Number_c, 1, [ 1, 2 ]), sc_initArray(Number_c, 1, [ 1, 2 ]),
              "PLANT", "WOOD"));
   this.pack.add(new Game_Card(12, "Door", sc_initArray(Number_c, 1, [ 2, 1 ]),
              sc_initArray(Number_c, 1, [ 1, 2 ]), sc_initArray(Number_c, 1, [ 1, 2 ]),
              sc_initArray(Number_c, 1, [ 1, 3 ]), sc_initArray(Number_c, 1, [ 1, 3 ]),
              "ITEM", "WOOD"));
   this.pack.add(new Game_Card(14, "Jeweled_Axe", sc_initArray(Number_c, 1, [ 5, 1 ]),
              sc_initArray(Number_c, 1, [ 5, 1 ]), sc_initArray(Number_c, 1, [ 4, 2 ]),
              sc_initArray(Number_c, 1, [ 4, 2 ]), sc_initArray(Number_c, 1, [ 4, 2 ]),
              "TOOL", "LUCKY"));
   this.pack.add(new Game_Card(15, "Simple Spade", sc_initArray(Number_c, 1, [ 2, 3 ]),
              sc_initArray(Number_c, 1, [ 2, 3 ]), sc_initArray(Number_c, 1, [ 3, 3 ]),
              sc_initArray(Number_c, 1, [ 3, 4 ]), sc_initArray(Number_c, 1, [ 3, 2 ]),
              "TOOL", "NEUTRAL"));
   this.pack.add(new Game_Card(16, "Fishing_Rod", sc_initArray(Number_c, 1, [ 1, 1 ]),
              sc_initArray(Number_c, 1, [ 1, 1 ]), sc_initArray(Number_c, 1, [ 1, 1 ]),
              sc_initArray(Number_c, 1, [ 1, 1 ]), sc_initArray(Number_c, 1, [ 1, 1 ]),
              "ITEM", "TOOL"));
   this.pack.add(new Game_Card(19, "Iron", sc_initArray(Number_c, 1, [ 3, 3 ]),
              sc_initArray(Number_c, 1, [ 3, 3 ]), sc_initArray(Number_c, 1, [ 3, 3 ]),
              sc_initArray(Number_c, 1, [ 3, 3 ]), sc_initArray(Number_c, 1, [ 3, 3 ]),
              "METAL", "NEUTRAL"));
   this.pack.add(new Game_Card(20, "Iron_Ore", sc_initArray(Number_c, 1, [ 1, 1 ]),
              sc_initArray(Number_c, 1, [ 2, 2 ]), sc_initArray(Number_c, 1, [ 3, 3 ]),
              sc_initArray(Number_c, 1, [ 2, 2 ]), sc_initArray(Number_c, 1, [ 1, 1 ]),
              "ORE", "METAL"));
   this.pack.add(new Game_Card(21, "Planks", sc_initArray(Number_c, 1, [ 3, 2 ]),
              sc_initArray(Number_c, 1, [ 3, 2 ]), sc_initArray(Number_c, 1, [ 2, 3 ]),
              sc_initArray(Number_c, 1, [ 2, 3 ]), sc_initArray(Number_c, 1, [ 2, 3 ]),
              "PLANT", "WOOD"));
   this.pack.add(new Game_Card(22, "Water_Bucket", sc_initArray(Number_c, 1, [ 2, 2 ]),
              sc_initArray(Number_c, 1, [ 1, 2 ]), sc_initArray(Number_c, 1, [ 2, 3 ]),
              sc_initArray(Number_c, 1, [ 2, 4 ]), sc_initArray(Number_c, 1, [ 3, 3 ]),
              "ITEM", "FARM"));
   this.pack.add(new Game_Card(23, "Gold", sc_initArray(Number_c, 1, [ 3, 3 ]),
              sc_initArray(Number_c, 1, [ 2, 2 ]), sc_initArray(Number_c, 1, [ 3, 3 ]),
              sc_initArray(Number_c, 1, [ 2, 2 ]), sc_initArray(Number_c, 1, [ 3, 3 ]),
              "METAL", "NEUTRAL"));
   this.pack.add(new Game_Card(24, "Anvil", sc_initArray(Number_c, 1, [ 3, 4 ]),
              sc_initArray(Number_c, 1, [ 4, 3 ]), sc_initArray(Number_c, 1, [ 3, 4 ]),
              sc_initArray(Number_c, 1, [ 3, 4 ]), sc_initArray(Number_c, 1, [ 3, 3 ]),
              "TOOL", "METAL"));
   this.pack.add(new Game_Card(25, "NameTag", sc_initArray(Number_c, 1, [ 1, 2 ]),
              sc_initArray(Number_c, 1, [ 1, 2 ]), sc_initArray(Number_c, 1, [ 1, 2 ]),
              sc_initArray(Number_c, 1, [ 1, 2 ]), sc_initArray(Number_c, 1, [ 1, 2 ]),
              "ITEM", "NEUTRAL"));
   this.pack.add(new Game_Card(28, "Furnace", sc_initArray(Number_c, 1, [ 2, 4 ]),
              sc_initArray(Number_c, 1, [ 2, 3 ]), sc_initArray(Number_c, 1, [ 3, 4 ]),
              sc_initArray(Number_c, 1, [ 4, 4 ]), sc_initArray(Number_c, 1, [ 3, 3 ]),
              "TOOL", "NEUTRAL"));
   this.pack.add(new Game_Card(29, "Trident", sc_initArray(Number_c, 1, [ 3, 3 ]),
              sc_initArray(Number_c, 1, [ 3, 3 ]), sc_initArray(Number_c, 1, [ 3, 3 ]),
              sc_initArray(Number_c, 1, [ 3, 3 ]), sc_initArray(Number_c, 1, [ 3, 3 ]),
              "WATER", "TOOL"));
   this.pack.add(new Game_Card(30, "Healing_Potion", sc_initArray(Number_c, 1, [ 0, 1 ]),
              sc_initArray(Number_c, 1, [ 0, 1 ]), sc_initArray(Number_c, 1, [ 1, 3 ]),
              sc_initArray(Number_c, 1, [ 1, 3 ]), sc_initArray(Number_c, 1, [ 2, 3 ]),
              "ITEM", "NEUTRAL"));
   this.pack.add(new Game_Card(31, "Wheat", sc_initArray(Number_c, 1, [ 2, 4 ]),
              sc_initArray(Number_c, 1, [ 2, 4 ]), sc_initArray(Number_c, 1, [ 2, 4 ]),
              sc_initArray(Number_c, 1, [ 2, 4 ]), sc_initArray(Number_c, 1, [ 2, 3 ]),
              "PLANT", "FARM"));
   this.pack.add(new Game_Card(32, "Coral", sc_initArray(Number_c, 1, [ 3, 3 ]),
              sc_initArray(Number_c, 1, [ 2, 3 ]), sc_initArray(Number_c, 1, [ 3, 4 ]),
              sc_initArray(Number_c, 1, [ 4, 4 ]), sc_initArray(Number_c, 1, [ 4, 5 ]),
              "WATER", "PLANT"));
   this.pack.add(new Game_Card(33, "Kelp", sc_initArray(Number_c, 1, [ 2, 4 ]),
              sc_initArray(Number_c, 1, [ 3, 4 ]), sc_initArray(Number_c, 1, [ 3, 3 ]),
              sc_initArray(Number_c, 1, [ 2, 3 ]), sc_initArray(Number_c, 1, [ 4, 4 ]),
              "WATER", "PLANT"));
   this.pack.add(new Game_Card(35, "Aquamarine", sc_initArray(Number_c, 1, [ 2, 3 ]),
              sc_initArray(Number_c, 1, [ 3, 3 ]), sc_initArray(Number_c, 1, [ 3, 3 ]),
              sc_initArray(Number_c, 1, [ 4, 4 ]), sc_initArray(Number_c, 1, [ 4, 5 ]),
              "WATER", "METAL"));
   this.pack.add(new Game_Card(36, "Gold_Ore", sc_initArray(Number_c, 1, [ 2, 2 ]),
              sc_initArray(Number_c, 1, [ 1, 1 ]), sc_initArray(Number_c, 1, [ 2, 2 ]),
              sc_initArray(Number_c, 1, [ 2, 2 ]), sc_initArray(Number_c, 1, [ 2, 2 ]),
              "ORE", "NEUTRAL"));
   this.pack.add(new Game_Card(37, "Poppy", sc_initArray(Number_c, 1, [ 2, 2 ]),
              sc_initArray(Number_c, 1, [ 2, 2 ]), sc_initArray(Number_c, 1, [ 2, 2 ]),
              sc_initArray(Number_c, 1, [ 1, 1 ]), sc_initArray(Number_c, 1, [ 2, 2 ]),
              "FLOWER", "NEUTRAL"));
   this.pack.add(new Game_Card(38, "Dandelion", sc_initArray(Number_c, 1, [ 3, 2 ]),
              sc_initArray(Number_c, 1, [ 3, 2 ]), sc_initArray(Number_c, 1, [ 2, 2 ]),
              sc_initArray(Number_c, 1, [ 2, 1 ]), sc_initArray(Number_c, 1, [ 3, 3 ]),
              "FLOWER", "NEUTRAL"));
   this.pack.add(new Game_Card(39, "Raw_Steak", sc_initArray(Number_c, 1, [ 3, 4 ]),
              sc_initArray(Number_c, 1, [ 2, 3 ]), sc_initArray(Number_c, 1, [ 3, 4 ]),
              sc_initArray(Number_c, 1, [ 2, 4 ]), sc_initArray(Number_c, 1, [ 3, 3 ]),
              "ANIMAL", "FARM"));
   this.pack.add(new Game_Card(40, "Wool", sc_initArray(Number_c, 1, [ 2, 2 ]),
              sc_initArray(Number_c, 1, [ 1, 2 ]), sc_initArray(Number_c, 1, [ 3, 3 ]),
              sc_initArray(Number_c, 1, [ 2, 4 ]), sc_initArray(Number_c, 1, [ 3, 4 ]),
              "FARM", "ANIMAL"));
   this.pack.add(new Game_Card(41, "Newton's_Apple", sc_initArray(Number_c, 1, [ 4, 2 ]),
              sc_initArray(Number_c, 1, [ 3, 1 ]), sc_initArray(Number_c, 1, [ 3, 1 ]),
              sc_initArray(Number_c, 1, [ 4, 4 ]), sc_initArray(Number_c, 1, [ 1, 1 ]),
              "PLANT", "LUCKY"));
};
Game_cardDatabase_c.formData = function ()  {
   this.formPack.add(new Game_ComboBuild(17, "Tree", sc_initArray(Number_c, 1, [ 2, 4 ]), "PLANT"));
   this.formPack.add(new Game_ComboBuild(18, "Flower_Bush", sc_initArray(Number_c, 1, [ 2, 2 ]), "FLOWER"));
   this.formPack.add(new Game_ComboBuild(26, "Item_Frame", sc_initArray(Number_c, 1, [ 2, 2 ]), "TOOL"));
   this.formPack.add(new Game_ComboBuild(27, "Stairs", sc_initArray(Number_c, 1, [ 1, 1 ]), "TOOL"));
   this.formPack.add(new Game_ComboBuild(34, "Ocean_Monument", sc_initArray(Number_c, 1, [ 3, 3 ]), "WATER"));
   this.formPack.add(new Game_ComboBuild(42, "Apple_Pie", sc_initArray(Number_c, 1, [ 5, 5 ]), "NEUTRAL"));
};

Game_cardDatabase_c._Game_cardDatabaseInit = function() {
   this.pack = new jv_ArrayList();
      ;
   this.formPack = new jv_ArrayList();
      ;
};

// Generated JS from Java: Game.ComboBuild -----
function Game_ComboBuild(ID, iName, core, type) {
   this.itemName = null;
   this.id = 0;
   this.coreMod = null;
   this.mainType = null;
   this._isBuildUsed = false;

   jv_Object.call(this);
   this._Game_ComboBuildInit();
   this.id = ID;
   this.itemName = iName;
   this.coreMod = core;
   this.mainType = type;
}

var Game_ComboBuild_c = sc_newClass("Game.ComboBuild", Game_ComboBuild, jv_Object, null);

Game_ComboBuild_c.getCoreMod = function ()  {
   return this.coreMod;
};
Game_ComboBuild_c.getItemName = function ()  {
   return this.itemName;
};
Game_ComboBuild_c.activeType = function ()  {
   return this.mainType;
};
Game_ComboBuild_c.getId = function ()  {
   return this.id;
};
Game_ComboBuild_c.useBuild = function ()  {
   this._isBuildUsed = true;
};
Game_ComboBuild_c.isBuildUsed = function ()  {
   return this._isBuildUsed;
};

Game_ComboBuild_c._Game_ComboBuildInit = function() {
   this._isBuildUsed = false;
      ;
};

// Generated JS from Java: Game.ComboBuild -----
function Game_ComboBuild(ID, iName, core, type) {
   this.itemName = null;
   this.id = 0;
   this.coreMod = null;
   this.mainType = null;
   this._isBuildUsed = false;

   jv_Object.call(this);
   this._Game_ComboBuildInit();
   this.id = ID;
   this.itemName = iName;
   this.coreMod = core;
   this.mainType = type;
}

var Game_ComboBuild_c = sc_newClass("Game.ComboBuild", Game_ComboBuild, jv_Object, null);

Game_ComboBuild_c.getCoreMod = function ()  {
   return this.coreMod;
};
Game_ComboBuild_c.getItemName = function ()  {
   return this.itemName;
};
Game_ComboBuild_c.activeType = function ()  {
   return this.mainType;
};
Game_ComboBuild_c.getId = function ()  {
   return this.id;
};
Game_ComboBuild_c.useBuild = function ()  {
   this._isBuildUsed = true;
};
Game_ComboBuild_c.isBuildUsed = function ()  {
   return this._isBuildUsed;
};

Game_ComboBuild_c._Game_ComboBuildInit = function() {
   this._isBuildUsed = false;
      ;
};

// Generated JS from Java: Game.cardDatabase -----
function Game_cardDatabase() {
   this.pack = null;
   this.formPack = null;
   jv_Object.call(this);
   this._Game_cardDatabaseInit();
}

var Game_cardDatabase_c = sc_newClass("Game.cardDatabase", Game_cardDatabase, jv_Object, null);

Game_cardDatabase_c.cardData = function ()  {
   this.pack.add(new Game_Card(-1, "Blank", sc_initArray(Number_c, 1, [ 0, 0 ]),
              sc_initArray(Number_c, 1, [ 0, 0 ]), sc_initArray(Number_c, 1, [ 0, 0 ]),
              sc_initArray(Number_c, 1, [ 0, 0 ]), sc_initArray(Number_c, 1, [ 0, 0 ]),
              "NEUTRAL", "NEUTRAL"));
   this.pack.add(new Game_Card(0, "Basic_Seeds", sc_initArray(Number_c, 1, [ 4, 2 ]),
              sc_initArray(Number_c, 1, [ 2, 2 ]), sc_initArray(Number_c, 1, [ 2, 3 ]),
              sc_initArray(Number_c, 1, [ 2, 4 ]), sc_initArray(Number_c, 1, [ 1, 4 ]),
              "PLANT", "FARM"));
   this.pack.add(new Game_Card(1, "Simple_Axe", sc_initArray(Number_c, 1, [ 4, 3 ]),
              sc_initArray(Number_c, 1, [ 3, 3 ]), sc_initArray(Number_c, 1, [ 3, 4 ]),
              sc_initArray(Number_c, 1, [ 3, 5 ]), sc_initArray(Number_c, 1, [ 2, 4 ]),
              "TOOL", "NEUTRAL"));
   this.pack.add(new Game_Card(2, "Feather", sc_initArray(Number_c, 1, [ 3, 1 ]),
              sc_initArray(Number_c, 1, [ 3, 1 ]), sc_initArray(Number_c, 1, [ 4, 2 ]),
              sc_initArray(Number_c, 1, [ 3, 3 ]), sc_initArray(Number_c, 1, [ 5, 3 ]),
              "ANIMAL", "FARM"));
   this.pack.add(new Game_Card(3, "Flaccid_Flower", sc_initArray(Number_c, 1, [ 2, 1 ]),
              sc_initArray(Number_c, 1, [ 1, 1 ]), sc_initArray(Number_c, 1, [ 1, 3 ]),
              sc_initArray(Number_c, 1, [ 1, 3 ]), sc_initArray(Number_c, 1, [ 1, 2 ]),
              "FLOWER", "PLANT"));
   this.pack.add(new Game_Card(4, "Gourd_Seeds", sc_initArray(Number_c, 1, [ 1, 4 ]),
              sc_initArray(Number_c, 1, [ 1, 4 ]), sc_initArray(Number_c, 1, [ 2, 4 ]),
              sc_initArray(Number_c, 1, [ 2, 4 ]), sc_initArray(Number_c, 1, [ 3, 4 ]),
              "PLANT", "FARM"));
   this.pack.add(new Game_Card(5, "Pumpkin", sc_initArray(Number_c, 1, [ 2, 5 ]),
              sc_initArray(Number_c, 1, [ 2, 5 ]), sc_initArray(Number_c, 1, [ 3, 5 ]),
              sc_initArray(Number_c, 1, [ 3, 5 ]), sc_initArray(Number_c, 1, [ 3, 5 ]),
              "PLANT", "FARM"));
   this.pack.add(new Game_Card(6, "Apple", sc_initArray(Number_c, 1, [ 3, 2 ]),
              sc_initArray(Number_c, 1, [ 3, 2 ]), sc_initArray(Number_c, 1, [ 2, 3 ]),
              sc_initArray(Number_c, 1, [ 2, 3 ]), sc_initArray(Number_c, 1, [ 2, 3 ]),
              "PLANT", "FARM"));
   this.pack.add(new Game_Card(7, "Mutton", sc_initArray(Number_c, 1, [ 4, 1 ]),
              sc_initArray(Number_c, 1, [ 3, 2 ]), sc_initArray(Number_c, 1, [ 3, 3 ]),
              sc_initArray(Number_c, 1, [ 3, 3 ]), sc_initArray(Number_c, 1, [ 2, 3 ]),
              "ANIMAL", "FARM"));
   this.pack.add(new Game_Card(8, "HayBale", sc_initArray(Number_c, 1, [ 3, 3 ]),
              sc_initArray(Number_c, 1, [ 3, 3 ]), sc_initArray(Number_c, 1, [ 2, 4 ]),
              sc_initArray(Number_c, 1, [ 2, 4 ]), sc_initArray(Number_c, 1, [ 2, 5 ]),
              "PLANT", "FARM"));
   this.pack.add(new Game_Card(9, "Shears", sc_initArray(Number_c, 1, [ 3, 1 ]),
              sc_initArray(Number_c, 1, [ 2, 1 ]), sc_initArray(Number_c, 1, [ 1, 2 ]),
              sc_initArray(Number_c, 1, [ 1, 2 ]), sc_initArray(Number_c, 1, [ 1, 2 ]),
              "ANIMAL", "FARM"));
   this.pack.add(new Game_Card(10, "Cornflower", sc_initArray(Number_c, 1, [ 2, 1 ]),
              sc_initArray(Number_c, 1, [ 2, 1 ]), sc_initArray(Number_c, 1, [ 1, 1 ]),
              sc_initArray(Number_c, 1, [ 1, 1 ]), sc_initArray(Number_c, 1, [ 1, 1 ]),
              "FLOWER", "FARM"));
   this.pack.add(new Game_Card(11, "Sapling", sc_initArray(Number_c, 1, [ 3, 1 ]),
              sc_initArray(Number_c, 1, [ 2, 1 ]), sc_initArray(Number_c, 1, [ 1, 2 ]),
              sc_initArray(Number_c, 1, [ 1, 2 ]), sc_initArray(Number_c, 1, [ 1, 2 ]),
              "PLANT", "WOOD"));
   this.pack.add(new Game_Card(12, "Door", sc_initArray(Number_c, 1, [ 2, 1 ]),
              sc_initArray(Number_c, 1, [ 1, 2 ]), sc_initArray(Number_c, 1, [ 1, 2 ]),
              sc_initArray(Number_c, 1, [ 1, 3 ]), sc_initArray(Number_c, 1, [ 1, 3 ]),
              "ITEM", "WOOD"));
   this.pack.add(new Game_Card(14, "Jeweled_Axe", sc_initArray(Number_c, 1, [ 5, 1 ]),
              sc_initArray(Number_c, 1, [ 5, 1 ]), sc_initArray(Number_c, 1, [ 4, 2 ]),
              sc_initArray(Number_c, 1, [ 4, 2 ]), sc_initArray(Number_c, 1, [ 4, 2 ]),
              "TOOL", "LUCKY"));
   this.pack.add(new Game_Card(15, "Simple Spade", sc_initArray(Number_c, 1, [ 2, 3 ]),
              sc_initArray(Number_c, 1, [ 2, 3 ]), sc_initArray(Number_c, 1, [ 3, 3 ]),
              sc_initArray(Number_c, 1, [ 3, 4 ]), sc_initArray(Number_c, 1, [ 3, 2 ]),
              "TOOL", "NEUTRAL"));
   this.pack.add(new Game_Card(16, "Fishing_Rod", sc_initArray(Number_c, 1, [ 1, 1 ]),
              sc_initArray(Number_c, 1, [ 1, 1 ]), sc_initArray(Number_c, 1, [ 1, 1 ]),
              sc_initArray(Number_c, 1, [ 1, 1 ]), sc_initArray(Number_c, 1, [ 1, 1 ]),
              "ITEM", "TOOL"));
   this.pack.add(new Game_Card(19, "Iron", sc_initArray(Number_c, 1, [ 3, 3 ]),
              sc_initArray(Number_c, 1, [ 3, 3 ]), sc_initArray(Number_c, 1, [ 3, 3 ]),
              sc_initArray(Number_c, 1, [ 3, 3 ]), sc_initArray(Number_c, 1, [ 3, 3 ]),
              "METAL", "NEUTRAL"));
   this.pack.add(new Game_Card(20, "Iron_Ore", sc_initArray(Number_c, 1, [ 1, 1 ]),
              sc_initArray(Number_c, 1, [ 2, 2 ]), sc_initArray(Number_c, 1, [ 3, 3 ]),
              sc_initArray(Number_c, 1, [ 2, 2 ]), sc_initArray(Number_c, 1, [ 1, 1 ]),
              "ORE", "METAL"));
   this.pack.add(new Game_Card(21, "Planks", sc_initArray(Number_c, 1, [ 3, 2 ]),
              sc_initArray(Number_c, 1, [ 3, 2 ]), sc_initArray(Number_c, 1, [ 2, 3 ]),
              sc_initArray(Number_c, 1, [ 2, 3 ]), sc_initArray(Number_c, 1, [ 2, 3 ]),
              "PLANT", "WOOD"));
   this.pack.add(new Game_Card(22, "Water_Bucket", sc_initArray(Number_c, 1, [ 2, 2 ]),
              sc_initArray(Number_c, 1, [ 1, 2 ]), sc_initArray(Number_c, 1, [ 2, 3 ]),
              sc_initArray(Number_c, 1, [ 2, 4 ]), sc_initArray(Number_c, 1, [ 3, 3 ]),
              "ITEM", "FARM"));
   this.pack.add(new Game_Card(23, "Gold", sc_initArray(Number_c, 1, [ 3, 3 ]),
              sc_initArray(Number_c, 1, [ 2, 2 ]), sc_initArray(Number_c, 1, [ 3, 3 ]),
              sc_initArray(Number_c, 1, [ 2, 2 ]), sc_initArray(Number_c, 1, [ 3, 3 ]),
              "METAL", "NEUTRAL"));
   this.pack.add(new Game_Card(24, "Anvil", sc_initArray(Number_c, 1, [ 3, 4 ]),
              sc_initArray(Number_c, 1, [ 4, 3 ]), sc_initArray(Number_c, 1, [ 3, 4 ]),
              sc_initArray(Number_c, 1, [ 3, 4 ]), sc_initArray(Number_c, 1, [ 3, 3 ]),
              "TOOL", "METAL"));
   this.pack.add(new Game_Card(25, "NameTag", sc_initArray(Number_c, 1, [ 1, 2 ]),
              sc_initArray(Number_c, 1, [ 1, 2 ]), sc_initArray(Number_c, 1, [ 1, 2 ]),
              sc_initArray(Number_c, 1, [ 1, 2 ]), sc_initArray(Number_c, 1, [ 1, 2 ]),
              "ITEM", "NEUTRAL"));
   this.pack.add(new Game_Card(28, "Furnace", sc_initArray(Number_c, 1, [ 2, 4 ]),
              sc_initArray(Number_c, 1, [ 2, 3 ]), sc_initArray(Number_c, 1, [ 3, 4 ]),
              sc_initArray(Number_c, 1, [ 4, 4 ]), sc_initArray(Number_c, 1, [ 3, 3 ]),
              "TOOL", "NEUTRAL"));
   this.pack.add(new Game_Card(29, "Trident", sc_initArray(Number_c, 1, [ 3, 3 ]),
              sc_initArray(Number_c, 1, [ 3, 3 ]), sc_initArray(Number_c, 1, [ 3, 3 ]),
              sc_initArray(Number_c, 1, [ 3, 3 ]), sc_initArray(Number_c, 1, [ 3, 3 ]),
              "WATER", "TOOL"));
   this.pack.add(new Game_Card(30, "Healing_Potion", sc_initArray(Number_c, 1, [ 0, 1 ]),
              sc_initArray(Number_c, 1, [ 0, 1 ]), sc_initArray(Number_c, 1, [ 1, 3 ]),
              sc_initArray(Number_c, 1, [ 1, 3 ]), sc_initArray(Number_c, 1, [ 2, 3 ]),
              "ITEM", "NEUTRAL"));
   this.pack.add(new Game_Card(31, "Wheat", sc_initArray(Number_c, 1, [ 2, 4 ]),
              sc_initArray(Number_c, 1, [ 2, 4 ]), sc_initArray(Number_c, 1, [ 2, 4 ]),
              sc_initArray(Number_c, 1, [ 2, 4 ]), sc_initArray(Number_c, 1, [ 2, 3 ]),
              "PLANT", "FARM"));
   this.pack.add(new Game_Card(32, "Coral", sc_initArray(Number_c, 1, [ 3, 3 ]),
              sc_initArray(Number_c, 1, [ 2, 3 ]), sc_initArray(Number_c, 1, [ 3, 4 ]),
              sc_initArray(Number_c, 1, [ 4, 4 ]), sc_initArray(Number_c, 1, [ 4, 5 ]),
              "WATER", "PLANT"));
   this.pack.add(new Game_Card(33, "Kelp", sc_initArray(Number_c, 1, [ 2, 4 ]),
              sc_initArray(Number_c, 1, [ 3, 4 ]), sc_initArray(Number_c, 1, [ 3, 3 ]),
              sc_initArray(Number_c, 1, [ 2, 3 ]), sc_initArray(Number_c, 1, [ 4, 4 ]),
              "WATER", "PLANT"));
   this.pack.add(new Game_Card(35, "Aquamarine", sc_initArray(Number_c, 1, [ 2, 3 ]),
              sc_initArray(Number_c, 1, [ 3, 3 ]), sc_initArray(Number_c, 1, [ 3, 3 ]),
              sc_initArray(Number_c, 1, [ 4, 4 ]), sc_initArray(Number_c, 1, [ 4, 5 ]),
              "WATER", "METAL"));
   this.pack.add(new Game_Card(36, "Gold_Ore", sc_initArray(Number_c, 1, [ 2, 2 ]),
              sc_initArray(Number_c, 1, [ 1, 1 ]), sc_initArray(Number_c, 1, [ 2, 2 ]),
              sc_initArray(Number_c, 1, [ 2, 2 ]), sc_initArray(Number_c, 1, [ 2, 2 ]),
              "ORE", "NEUTRAL"));
   this.pack.add(new Game_Card(37, "Poppy", sc_initArray(Number_c, 1, [ 2, 2 ]),
              sc_initArray(Number_c, 1, [ 2, 2 ]), sc_initArray(Number_c, 1, [ 2, 2 ]),
              sc_initArray(Number_c, 1, [ 1, 1 ]), sc_initArray(Number_c, 1, [ 2, 2 ]),
              "FLOWER", "NEUTRAL"));
   this.pack.add(new Game_Card(38, "Dandelion", sc_initArray(Number_c, 1, [ 3, 2 ]),
              sc_initArray(Number_c, 1, [ 3, 2 ]), sc_initArray(Number_c, 1, [ 2, 2 ]),
              sc_initArray(Number_c, 1, [ 2, 1 ]), sc_initArray(Number_c, 1, [ 3, 3 ]),
              "FLOWER", "NEUTRAL"));
   this.pack.add(new Game_Card(39, "Raw_Steak", sc_initArray(Number_c, 1, [ 3, 4 ]),
              sc_initArray(Number_c, 1, [ 2, 3 ]), sc_initArray(Number_c, 1, [ 3, 4 ]),
              sc_initArray(Number_c, 1, [ 2, 4 ]), sc_initArray(Number_c, 1, [ 3, 3 ]),
              "ANIMAL", "FARM"));
   this.pack.add(new Game_Card(40, "Wool", sc_initArray(Number_c, 1, [ 2, 2 ]),
              sc_initArray(Number_c, 1, [ 1, 2 ]), sc_initArray(Number_c, 1, [ 3, 3 ]),
              sc_initArray(Number_c, 1, [ 2, 4 ]), sc_initArray(Number_c, 1, [ 3, 4 ]),
              "FARM", "ANIMAL"));
   this.pack.add(new Game_Card(41, "Newton's_Apple", sc_initArray(Number_c, 1, [ 4, 2 ]),
              sc_initArray(Number_c, 1, [ 3, 1 ]), sc_initArray(Number_c, 1, [ 3, 1 ]),
              sc_initArray(Number_c, 1, [ 4, 4 ]), sc_initArray(Number_c, 1, [ 1, 1 ]),
              "PLANT", "LUCKY"));
};
Game_cardDatabase_c.formData = function ()  {
   this.formPack.add(new Game_ComboBuild(17, "Tree", sc_initArray(Number_c, 1, [ 2, 4 ]), "PLANT"));
   this.formPack.add(new Game_ComboBuild(18, "Flower_Bush", sc_initArray(Number_c, 1, [ 2, 2 ]), "FLOWER"));
   this.formPack.add(new Game_ComboBuild(26, "Item_Frame", sc_initArray(Number_c, 1, [ 2, 2 ]), "TOOL"));
   this.formPack.add(new Game_ComboBuild(27, "Stairs", sc_initArray(Number_c, 1, [ 1, 1 ]), "TOOL"));
   this.formPack.add(new Game_ComboBuild(34, "Ocean_Monument", sc_initArray(Number_c, 1, [ 3, 3 ]), "WATER"));
   this.formPack.add(new Game_ComboBuild(42, "Apple_Pie", sc_initArray(Number_c, 1, [ 5, 5 ]), "NEUTRAL"));
};

Game_cardDatabase_c._Game_cardDatabaseInit = function() {
   this.pack = new jv_ArrayList();
      ;
   this.formPack = new jv_ArrayList();
      ;
};

// Generated JS from Java: Game.Card -----
function Game_Card(ID, name, uAtk, atk, cDef, corn, def, prime, secondary) {
   this.cardName = null;
   this.id = 0;
   this.item = false;
   this.cardTypeONE = null;
   this.cardTypeTWO = null;
   this.positionMap = null;

   jv_Object.call(this);
   this._Game_CardInit();
   this.cardName = name;
   this.id = ID;
   this.positionMap.put(UBER, uAtk);
   this.positionMap.put(ATTACK, atk);
   this.positionMap.put(CoreDEFENCE, cDef);
   this.positionMap.put(CORE, corn);
   this.positionMap.put(DEFENCE, def);
   this.cardTypeONE = prime;
   this.cardTypeTWO = secondary;
}

var Game_Card_c = sc_newClass("Game.Card", Game_Card, jv_Object, null);

Game_Card_c.toString = function ()  {
   if (this.hasOwnProperty("$protoName")) {
      return jv_Class_c.toString.apply(this, arguments);
   }
   return this.id + " " + this.cardName + " " + this.cardTypeONE + " " + this.cardTypeTWO + " ";
};
Game_Card_c.getValue = function (boardPos)  {
   if (arguments.length == 0) return;
   return this.positionMap.get(boardPos);
};
Game_Card_c.activeTypeOne = function ()  {
   return this.cardTypeONE;
};
Game_Card_c.activeTypeTwo = function ()  {
   return this.cardTypeTWO;
};
Game_Card_c.useAsItem = function ()  {
   this.item = true;
};
Game_Card_c.isItem = function ()  {
   return this.item;
};
Game_Card_c.getId = function ()  {
   return this.id;
};

Game_Card_c._Game_CardInit = function() {
   this.item = false;
      ;
   this.positionMap = new jv_HashMap();
      ;
};

// Generated JS from Java: Game.ComboBuild -----
function Game_ComboBuild(ID, iName, core, type) {
   this.itemName = null;
   this.id = 0;
   this.coreMod = null;
   this.mainType = null;
   this._isBuildUsed = false;

   jv_Object.call(this);
   this._Game_ComboBuildInit();
   this.id = ID;
   this.itemName = iName;
   this.coreMod = core;
   this.mainType = type;
}

var Game_ComboBuild_c = sc_newClass("Game.ComboBuild", Game_ComboBuild, jv_Object, null);

Game_ComboBuild_c.getCoreMod = function ()  {
   return this.coreMod;
};
Game_ComboBuild_c.getItemName = function ()  {
   return this.itemName;
};
Game_ComboBuild_c.activeType = function ()  {
   return this.mainType;
};
Game_ComboBuild_c.getId = function ()  {
   return this.id;
};
Game_ComboBuild_c.useBuild = function ()  {
   this._isBuildUsed = true;
};
Game_ComboBuild_c.isBuildUsed = function ()  {
   return this._isBuildUsed;
};

Game_ComboBuild_c._Game_ComboBuildInit = function() {
   this._isBuildUsed = false;
      ;
};

// Generated JS from Java: Game.Card -----
function Game_Card(ID, name, uAtk, atk, cDef, corn, def, prime, secondary) {
   this.cardName = null;
   this.id = 0;
   this.item = false;
   this.cardTypeONE = null;
   this.cardTypeTWO = null;
   this.positionMap = null;

   jv_Object.call(this);
   this._Game_CardInit();
   this.cardName = name;
   this.id = ID;
   this.positionMap.put(UBER, uAtk);
   this.positionMap.put(ATTACK, atk);
   this.positionMap.put(CoreDEFENCE, cDef);
   this.positionMap.put(CORE, corn);
   this.positionMap.put(DEFENCE, def);
   this.cardTypeONE = prime;
   this.cardTypeTWO = secondary;
}

var Game_Card_c = sc_newClass("Game.Card", Game_Card, jv_Object, null);

Game_Card_c.toString = function ()  {
   if (this.hasOwnProperty("$protoName")) {
      return jv_Class_c.toString.apply(this, arguments);
   }
   return this.id + " " + this.cardName + " " + this.cardTypeONE + " " + this.cardTypeTWO + " ";
};
Game_Card_c.getValue = function (boardPos)  {
   if (arguments.length == 0) return;
   return this.positionMap.get(boardPos);
};
Game_Card_c.activeTypeOne = function ()  {
   return this.cardTypeONE;
};
Game_Card_c.activeTypeTwo = function ()  {
   return this.cardTypeTWO;
};
Game_Card_c.useAsItem = function ()  {
   this.item = true;
};
Game_Card_c.isItem = function ()  {
   return this.item;
};
Game_Card_c.getId = function ()  {
   return this.id;
};

Game_Card_c._Game_CardInit = function() {
   this.item = false;
      ;
   this.positionMap = new jv_HashMap();
      ;
};

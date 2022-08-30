import dev.robocode.tankroyale.botapi.Bot
import dev.robocode.tankroyale.botapi.BotInfo
import dev.robocode.tankroyale.botapi.Color
import dev.robocode.tankroyale.botapi.events.ScannedBotEvent
import dev.robocode.tankroyale.botapi.events.WonRoundEvent

var isScanning = false

fun quantEnemy(bot:Bot): Int {
    var enemies = bot.enemyCount
    return enemies
}

fun onScannedBotSolo(bot: Bot) {
    isScanning = true

    var distanceBot = bot.distanceTo(bot.x,bot.y)

    val bearingFromGun: Double = bot.gunBearingTo(bot.x, bot.y)

    bot.turnGunLeft(bearingFromGun)

    if ((distanceBot > 300.0) or (bot.energy < 50.0)){
        bot.bulletColor = Color.YELLOW
        bot.fire(1.0)
    }else if (distanceBot > 100.0){
        bot.bulletColor = Color.BLUE
        bot.fire(1.75)
    }else{
        bot.bulletColor = Color.GREEN
        bot.fire(3.0)
    }
    if (bearingFromGun > 5) {
        bot.rescan()
    }

    isScanning = false
}


//fun onWonRound(bot: WonRoundEvent?) {
//    bot.turnLeft(3600)
//}
/*
class TrackFire: Bot(BotInfo.fromFile("res/Ronald.json")) {
    var isScanning = false

    // Called when a new round is started -> initialize and do some movement
    override fun run() {
        isScanning = false

        val brown = Color.fromString("#A52A2A")
        gunColor = Color.YELLOW
        tracksColor = Color.YELLOW
        bodyColor = brown
        turretColor = Color.YELLOW
        radarColor = Color.YELLOW
        scanColor = Color.YELLOW
        bulletColor = Color.YELLOW

        // Loop while running
        while (isRunning) {
            if (isScanning) {
                go() // skip turn if we a scanning
            } else {
                turnGunLeft(10.0) // Scans automatically as radar is mounted on gun
                turnGunRight(20.0)
            }
        }
    }

    // We scanned another bot -> we have a target, so go get it
    override fun onScannedBot(e: ScannedBotEvent) {
        isScanning = true // we started scanning

        var distanceBot = distanceTo(e.x,e.y)
        //println(distanceBot)

        // Calculate direction of the scanned bot and bearing to it for the gun
        val bearingFromGun = gunBearingTo(e.x, e.y)
        println(bearingFromGun)
        // Turn the gun toward the scanned bot
        turnGunLeft(bearingFromGun)

        if ((distanceBot > 300.0) or (this.energy < 50.0)){
            bulletColor = Color.YELLOW
            fire(1.0)
        }else if (distanceBot > 100.0){
            bulletColor = Color.BLUE
            fire(1.75)
        }else{
            bulletColor = Color.GREEN
            fire(3.0)
        }

        // Generates another scan event if we see a bot.
        // We only need to call this if the gun (and therefore radar)
        // are not turning. Otherwise, scan is called automatically.
        if (bearingFromGun > 5) {
            rescan()
        }
        isScanning = false // we stopped scanning
    }

    // We won the round -> do a victory dance!
    override fun onWonRound(e: WonRoundEvent) {
        // Victory dance turning right 360 degrees 100 times
        turnLeft(36000.0)
    }

}
// The main method starts our bot
fun main(args: Array<String>){
    TrackFire().start()
}

 */
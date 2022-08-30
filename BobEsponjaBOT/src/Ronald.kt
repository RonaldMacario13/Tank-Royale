import dev.robocode.tankroyale.botapi.Bot
import dev.robocode.tankroyale.botapi.BotInfo
import dev.robocode.tankroyale.botapi.Color
import dev.robocode.tankroyale.botapi.events.BulletHitBotEvent
import dev.robocode.tankroyale.botapi.events.HitWallEvent
import dev.robocode.tankroyale.botapi.events.ScannedBotEvent
import dev.robocode.tankroyale.botapi.events.WonRoundEvent

class BobEsponjaBOT: Bot(BotInfo.fromFile("res/Ronald.json")) {

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

        while (isRunning) {
            if (isScanning) {
                go()
            } else {
                turnGunLeft(10.0)
                turnGunRight(20.0)
            }
        }
    }

    override fun onScannedBot(e: ScannedBotEvent) {
        isScanning = true

        val distanceBot = distanceTo(e.x,e.y)
        //println(distanceBot)

        val bearingFromGun = gunBearingTo(e.x, e.y)
        //println(bearingFromGun)

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

        if (bearingFromGun > 5) {
            rescan()
        }
        isScanning = false
    }

    override fun onHitByBullet(bulletHitBotEvent: BulletHitBotEvent) {
        //turnLeft(normalizeAbsoluteAngle(90 - bulletHitBotEvent.bullet.direction))
        forward(50.0)
        back(10.0)
    }

    override fun onHitWall(botHitWallEvent: HitWallEvent?) {
        turnLeft(90.0)
        forward(50.0)
    }

    override fun onWonRound(e: WonRoundEvent) {
        turnLeft(36000.0)
    }

}
fun main(){
    BobEsponjaBOT().start()
}
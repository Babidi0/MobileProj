package com.example.progetto.utilities

import com.example.progetto.data.database.ProjDAO
import com.example.progetto.data.database.User
import org.mindrot.jbcrypt.BCrypt


fun hashPassword(password: String): String {
    return BCrypt.hashpw(password, BCrypt.gensalt())
}

fun verifyPassword(password: String, hashedPassword: String): Boolean {
    return BCrypt.checkpw(password, hashedPassword)
}

/*suspend fun registerUser(username: String, password: String, Img: String, userDao: ProjDAO){
    val hashedPassword = hashPassword(password)
    //aggiungi una fun per aggiungere delle immagini
    val user = User(username = username, passwordHash = hashedPassword , userImg = Img)
    userDao.addUser(user)
}

 */

 suspend fun authenticateUser(username: String, password: String, userDao: ProjDAO): Boolean{
    val user = userDao.getUserByUsername(username) ?: return false
    return verifyPassword(password, user.passwordHash)

}



/*
l'elementi con salt lo decidi successivamente
fun generateSalt() : String {
    val random = SecureRandom()
    val salt = ByteArray(16)
    random.nextBytes(salt)
    return Base64.getEncoder().encodeToString(salt)

}
 */
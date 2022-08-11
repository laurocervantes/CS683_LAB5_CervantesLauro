package edu.bu.projectportal.datalayer

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class ProjectRepository(private val projectDao: ProjectDao) {

    val allProjects: Flow<List<Project>> = projectDao.getAllProjects()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(project: Project){
        projectDao.addProject(project)
    }

}
package com.hsd.gitlab.outgoing.slack;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.hsd.gitlab.systemhook.bean.Author;
import com.hsd.gitlab.systemhook.bean.Commits;
import com.hsd.gitlab.systemhook.bean.Project;
import com.hsd.gitlab.systemhook.bean.event.PushEvent;
 
 
public class ChatbotSend {
 
    public static String WEBHOOK_TOKEN = "https://hooks.slack.com/services/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";
 
    /**
     * payload={"text": "A very important thing has occurred! <https://alert-system.com/alerts/1234|Click here> for details!"}
     *
     * @version Oct 9, 201710:09:13 AM
     * @author Ford.CHEN
     * @throws Exception
     */
    @Test
    public void textMessage() throws Exception{
 
        HttpClient httpclient = HttpClients.createDefault();
 
        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
 
        String textMsg = "{\"text\": \"A very important thing has occurred! <http://192.168.1.239:8936/gold/hsdgold-portal-pc/commit/5ea4ea0679edd0c2401fd71c6004131f517f7f31|提金图片路径问题修复> for details!\"}";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);
 
        HttpResponse response = httpclient.execute(httppost);
        if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
            String result= EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(result);
        }
    }
    
    @Test
    public void textComposeBasicMessage() throws Exception{
        
        PushEvent event = new PushEvent();
        event.setUsername("Uname.CHEN");
        event.setUserUsername("linzhiyong");
        event.setRef("ref/orgin/master");
        event.setUserAvatar("http://192.168.1.239:8936/uploads/system/user/avatar/40/QQ%E6%88%AA%E5%9B%BE20170615104243.png");
        
        Project project = new Project();
        project.setName("hsd-user-service");
        event.setProject(project);
        
        List<Commits> commits = new ArrayList<Commits>();
        event.setCommits(commits);
        Commits commit = new Commits();
        commit.setUrl("http://192.168.1.239:8936/gold/hsdgold-portal-wap/commit/b8cd2d6b4542b787d5747c95d326bab5ae661d41");
        
        Author author = new Author();
        author.setName("孩子他爸");
        commit.setAuthor(author);;
        commit.setMessage("提金图片路径问题修复");
        commit.setTimestamp(new Date());
        commits.add(commit);
        
        
 
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
 
        String textMsg = event.toSlackBasicMessageJson();
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);
 
        HttpResponse response = httpclient.execute(httppost);
        if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
            String result= EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(result);
        }
    }
    
    
    @Test
    public void textComposeAttachMessage() throws Exception{
        
        PushEvent event = new PushEvent();
        event.setUsername("Uname.CHEN");
        event.setUserUsername("linzhiyong");
        event.setRef("ref/orgin/master");
        event.setUserAvatar("http://192.168.1.239:8936/uploads/system/user/avatar/40/QQ%E6%88%AA%E5%9B%BE20170615104243.png");
        
        Project project = new Project();
        project.setName("hsd-user-service");
        project.setNamespace("gold");
        event.setProject(project);
        
        List<Commits> commits = new ArrayList<Commits>();
        event.setCommits(commits);
        Commits commit = new Commits();
        commit.setUrl("http://192.168.1.239:8936/gold/hsdgold-portal-wap/commit/b8cd2d6b4542b787d5747c95d326bab5ae661d41");
        
        Author author = new Author();
        author.setName("孩子他爸");
        commit.setAuthor(author);;
        commit.setMessage("提金图片路径问题修复");
        commit.setTimestamp(new Date());
        commits.add(commit);
        
        
        
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        
        String textMsg = event.toSlackAttachMessageJson();
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);
        
        HttpResponse response = httpclient.execute(httppost);
        if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
            String result= EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(result);
        }
    }
    
    
}
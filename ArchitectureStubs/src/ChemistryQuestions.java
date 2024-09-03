public class ChemistryQuestions extends Questions
{
    @Override
    public void setQuestion(String question, String answer)
    {
        //TODO: implement method
        questionMap.put(question, answer);

    }

    @Override
    public String getAnswer(String question)
    {
        //TODO: implement method
        //returns answer to given question
        return questionMap.get(question);
    }

    @Override
    public String getQuestion(String answer)
    {
        //TODO: implement method
        return questionMap.get(answer);

    }

    @Override
    public void setAnswer(String question, String answer)
    {
        questionMap.put(question, answer);
    }
}

class ClassB
{
    private ClassC cc = new ClassC();
    public int someMethod(int aParam)
    {
        ClassC.otherMethod();
        return 5;
    }
}
